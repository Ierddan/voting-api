package com.ierddan.votingapi.service;

import com.ierddan.votingapi.entity.SessionVoting;
import com.ierddan.votingapi.entity.VotingTheme;
import com.ierddan.votingapi.repository.SessionVotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionVotingService {

    private static final String DEACTIVATED = "DEACTIVATED";

    @Autowired
    private final SessionVotingRepository repository;

    @Autowired
    public final VotingThemeService votingThemeService;

    public SessionVotingService(SessionVotingRepository repository, VotingThemeService votingThemeService) {
        this.repository = repository;
        this.votingThemeService = votingThemeService;
    }

    public SessionVoting createSessionVoting(Long idVotingTheme) {
        VotingTheme votingTheme = votingThemeService.findVotingThemeById(idVotingTheme);

        SessionVoting sessionVoting = new SessionVoting(votingTheme);
        try {
            return repository.save(sessionVoting);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar objeto SesseionVoting");
        }
    }

    public SessionVoting findByIdSessionVoting(Long idSessionVoting) {
        try {
            SessionVoting sessionVoting = repository.findByIdSessionVoting(idSessionVoting);
            disableSessionVoting(sessionVoting);
            return sessionVoting;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto SessionVoting");
        }
    }

    public List<SessionVoting> findAllSessionVoting() {
        try {
            List<SessionVoting> sessionVotings = repository.findAll();
            for (SessionVoting sessionVoting : sessionVotings) {
                disableSessionVoting(sessionVoting);
            }
            return sessionVotings;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar lista de sessões.", e);
        }
    }

    public SessionVoting closeSessionVoting(Long idSessionVoting) {
        SessionVoting currentSessionVoting = findByIdSessionVoting(idSessionVoting);
        if (!currentSessionVoting.getActive().equals("ACTIVE")) {
            throw new IllegalStateException("Session Voting já está fechada!");
        }
        currentSessionVoting.setActive(DEACTIVATED);
        return repository.save(currentSessionVoting);
    }

    private void disableSessionVoting(SessionVoting sessionVoting) {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime limit = sessionVoting.getStartVoting().plusMinutes(1);
        if (current.isAfter(limit)) {
            sessionVoting.setActive(DEACTIVATED);
        }
    }
}

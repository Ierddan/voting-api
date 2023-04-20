package com.ierddan.votingapi.service;

import com.ierddan.votingapi.entity.SessionVoting;
import com.ierddan.votingapi.entity.VotingTheme;
import com.ierddan.votingapi.repository.SessionVotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionVotingService {

    private static final String DEACTIVATED = "DEACTIVATED";
    private static final int TIME_SESSION = 1;

    private final SessionVotingRepository repository;

    public final VotingThemeService votingThemeService;

    @Autowired
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
            validateSessionVoting(sessionVoting);
            return sessionVoting;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto SessionVoting");
        }
    }

    public List<SessionVoting> findAllSessionVoting() {
        try {
            List<SessionVoting> sessionVotings = repository.findAll();
            for (SessionVoting sessionVoting : sessionVotings) {
                validateSessionVoting(sessionVoting);
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

    public void validateSessionVoting(SessionVoting sessionVoting) {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime limit = sessionVoting.getStartVoting().plusMinutes(TIME_SESSION);
        if (current.isAfter(limit)) {
            sessionVoting.setActive(DEACTIVATED);
        }
    }
}

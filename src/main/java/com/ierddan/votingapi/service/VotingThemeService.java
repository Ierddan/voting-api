package com.ierddan.votingapi.service;

import com.ierddan.votingapi.entity.VotingTheme;
import com.ierddan.votingapi.repository.VotingThemeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingThemeService {

    private final Logger logger = LoggerFactory.getLogger(VotingThemeService.class);

    private final VotingThemeRepository repository;

    @Autowired
    public VotingThemeService(VotingThemeRepository repository) {
        this.repository = repository;
    }

    public VotingTheme saveVotingTheme(VotingTheme votingTheme) {
        logger.info("class=VotingThemeService method=saveVotingTheme votingTheme=" + votingTheme.toString());
        try {
            return repository.save(votingTheme);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o objeto VotingTheme");
        }
    }

    public List<VotingTheme> getAllVotingThemes() {
        logger.info("class=VotingThemeService method=getAllVotingThemes");
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a lista de VotingThemes");
        }
    }

    public VotingTheme findVotingThemeById(Long id) {
        logger.info("class=VotingThemeService method=findVotingThemeById");
        try {
            return repository.findByIdVotingTheme(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto VotingTheme");
        }
    }

    public void deleteVotingTheme(Long id) {
        logger.info("class=VotingThemeService method=deleteVotingTheme");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto VotingTheme");
        }
    }
}

package com.ierddan.votingapi.service;

import com.ierddan.votingapi.entity.VotingTheme;
import com.ierddan.votingapi.repository.VotingThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingThemeService {

    @Autowired
    private final VotingThemeRepository repository;

    public VotingThemeService(VotingThemeRepository repository) {
        this.repository = repository;
    }

    public VotingTheme saveVotingTheme(VotingTheme votingTheme) {
        try {
            return repository.save(votingTheme);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o objeto VotingTheme");
        }
    }

    public List<VotingTheme> getAllVotingThemes() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a lista de VotingThemes");
        }
    }

    public VotingTheme findVotingThemeById(Long id) {
        try {
            return repository.findByIdVotingTheme(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto VotingTheme");
        }
    }

    public void deleteVotingTheme(Long id) {
        try{
            repository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto VotingTheme");
        }
    }
}

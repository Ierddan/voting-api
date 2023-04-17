package com.ierddan.votingapi.resource.votingtheme;

import com.ierddan.votingapi.entity.VotingTheme;
import com.ierddan.votingapi.service.VotingThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/voting-themes")
public class VotingThemeResource {
    @Autowired
    private final VotingThemeService service;

    public VotingThemeResource(VotingThemeService service) {
        this.service = service;
    }

    @GetMapping
    public List<VotingTheme> getAllVotingThemes() {
        return service.getAllVotingThemes();
    }

    @PostMapping("/voting-theme")
    public VotingTheme saveVotingTheme(
            @RequestBody VotingTheme votingTheme) {
        return service.saveVotingTheme(votingTheme);
    }

    @GetMapping("/voting-theme/{id}")
    public VotingTheme getVotingThemeById(@PathVariable Long id) {
        return service.findVotingThemeById(id);
    }

    @DeleteMapping("/voting-theme/{id}")
    public void deleteVotingTheme(@PathVariable Long id) {
        service.deleteVotingTheme(id);
    }
}

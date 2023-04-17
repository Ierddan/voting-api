package com.ierddan.votingapi.repository;

import com.ierddan.votingapi.entity.VotingTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingThemeRepository extends JpaRepository<VotingTheme, Long> {

    VotingTheme findByTitle(String title);

    VotingTheme findByIdVotingTheme(Long id);
}

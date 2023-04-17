package com.ierddan.votingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "VOTING_THEME")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VotingTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voting_theme_gen")
    @SequenceGenerator(name = "voting_theme_gen", sequenceName = "voting_theme_seq")
    @Column(name = "ID_VOTING_THEME", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long idVotingTheme;

    @Column(name = "TITLE", nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

}

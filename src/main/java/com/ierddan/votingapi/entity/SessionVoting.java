package com.ierddan.votingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "SESSION_VOTING")
@Data
@NoArgsConstructor
public class SessionVoting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "session_voting_gen", sequenceName = "session_voting_seq")
    @Column(name = "ID_SESSION_VOTING", nullable = false)
    private Long idSessionVoting;

    @JoinColumn(referencedColumnName = "ID_VOTING_THEME")
    @OneToOne
    private VotingTheme idVotingTheme;

    @Column(name = "START_VOTING")
    private LocalDateTime startVoting = LocalDateTime.now();

    @Column(name = "ACTIVE", nullable = false)
    private String active = "ACTIVE";

    public SessionVoting(VotingTheme votingTheme) {
        this.idVotingTheme = votingTheme;
    }

}

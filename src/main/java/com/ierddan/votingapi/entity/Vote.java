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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "VOTE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_GEN")
    @SequenceGenerator(name = "VOTE_GEN", sequenceName = "VOTE_SEQ")
    @Column(name = "ID", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JoinColumn(referencedColumnName = "ID_SESSION_VOTING")
    @OneToOne
    private SessionVoting idSessionVoting;

    @JoinColumn(referencedColumnName = "ID_ASSOCIATE")
    @OneToOne
    private Associate associate;

    @Column(name = "VOTE", nullable = false)
    private String vote;

    public Vote(SessionVoting idSessionVoting, Associate associate, String vote) {
        this.idSessionVoting = idSessionVoting;
        this.associate = associate;
        this.vote = vote;
    }
}

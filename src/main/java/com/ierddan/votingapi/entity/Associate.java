package com.ierddan.votingapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "VOTER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associate_gen")
    @SequenceGenerator(name = "associate_gen", sequenceName = "associate_seq")
    @Column(name = "ID_ASSOCIATE", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long idAssociate;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ASSOCIATE_CPF", nullable = false)
    private String cpf;

    @Column(name = "ADRESS_CODE", nullable = false)
    private String addressCode;
}

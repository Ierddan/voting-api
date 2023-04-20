package com.ierddan.votingapi.repository;

import com.ierddan.votingapi.entity.Vote;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT COUNT(v) > 0 FROM Vote v WHERE v.idSessionVoting.idSessionVoting = :ID AND v.associate.cpf = :CPF")
    boolean validatedVote(@Param("ID") Long idSessionVoting, @Param("CPF") String cpf);

}
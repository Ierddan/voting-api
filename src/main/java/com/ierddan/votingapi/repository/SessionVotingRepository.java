package com.ierddan.votingapi.repository;

import com.ierddan.votingapi.entity.SessionVoting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionVotingRepository extends JpaRepository<SessionVoting, Long> {

    SessionVoting findByIdSessionVoting(Long id);
}



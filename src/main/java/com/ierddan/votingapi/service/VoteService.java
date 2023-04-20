package com.ierddan.votingapi.service;

import com.ierddan.votingapi.resource.vote.request.VoteInput;
import com.ierddan.votingapi.entity.Associate;
import com.ierddan.votingapi.entity.SessionVoting;
import com.ierddan.votingapi.entity.Vote;
import com.ierddan.votingapi.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final Logger logger = LoggerFactory.getLogger(VoteService.class);

    private final VoteRepository repository;

    public final AssociateService associateService;

    public final SessionVotingService sessionVotingService;

    @Autowired
    public VoteService(VoteRepository repository, AssociateService associateService, SessionVotingService sessionVotingService) {
        this.repository = repository;
        this.associateService = associateService;
        this.sessionVotingService = sessionVotingService;
    }

    public Vote createVoting(VoteInput voteInput) {
        logger.info("class=VoteService method=CreateVoting voteInput=" + voteInput.toString());
        if (repository.validatedVote(voteInput.getIdSessionVoting(), voteInput.getCpf())) {
            throw new RuntimeException("Associate has already voted!");
        }

        Associate associate = associateService.findAssociateByCpf(voteInput.getCpf());
        SessionVoting sessionVoting = sessionVotingService.findByIdSessionVoting(voteInput.getIdSessionVoting());

        Vote voting = new Vote(sessionVoting, associate, voteInput.getVote());
        try {
            return repository.save(voting);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }
}

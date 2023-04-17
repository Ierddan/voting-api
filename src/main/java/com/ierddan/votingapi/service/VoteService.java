package com.ierddan.votingapi.service;

import com.ierddan.votingapi.resource.vote.request.VoteInput;
import com.ierddan.votingapi.entity.Associate;
import com.ierddan.votingapi.entity.SessionVoting;
import com.ierddan.votingapi.entity.Vote;
import com.ierddan.votingapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private final VoteRepository repository;

    @Autowired
    public final AssociateService associateService;

    @Autowired
    public final SessionVotingService sessionVotingService;

    public VoteService(VoteRepository repository, AssociateService associateService, SessionVotingService sessionVotingService) {
        this.repository = repository;
        this.associateService = associateService;
        this.sessionVotingService = sessionVotingService;
    }

    public Vote createVoting(VoteInput voteInput) {
        Associate associate = associateService.findAssociateByCpf(voteInput.getCpf());
        SessionVoting sessionVoting = sessionVotingService.findByIdSessionVoting(voteInput.getIdSessionVoting());

        Vote voting = new Vote(sessionVoting, associate, voteInput.getVote());
        try {
            return repository.save(voting);
        }catch(Exception e){
            throw new RuntimeException("");
        }
    }
}

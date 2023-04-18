package com.ierddan.votingapi.resource.vote;

import com.ierddan.votingapi.resource.vote.request.VoteInput;
import com.ierddan.votingapi.entity.Vote;
import com.ierddan.votingapi.resource.vote.response.VoteOutput;
import com.ierddan.votingapi.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/votings")
public class VoteRsource {

    @Autowired
    private final VoteService service;

    public VoteRsource(VoteService service) {
        this.service = service;
    }

    @PostMapping("/voting")
    public Vote createVoting(@RequestBody VoteInput voteInput) {
        return service.createVoting(voteInput);
    }

//    @GetMapping("/poll-result")
//    public VoteOutput pollResult(Long id){
//
//    }
}

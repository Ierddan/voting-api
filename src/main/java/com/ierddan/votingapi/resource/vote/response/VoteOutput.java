package com.ierddan.votingapi.resource.vote.response;

import lombok.Data;

@Data
public class VoteOutput {

    private Long idVotingTheme;
    private int VoteYes;
    private int VoteNo;
    private int totalVotes;
}

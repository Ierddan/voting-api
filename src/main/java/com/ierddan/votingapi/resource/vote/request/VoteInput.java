package com.ierddan.votingapi.resource.vote.request;

import lombok.Data;

@Data
public class VoteInput {

    private Long idSessionVoting;
    private String cpf;
    private String vote;

}

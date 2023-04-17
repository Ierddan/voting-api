package com.ierddan.votingapi.resource.sessionvoting;

import com.ierddan.votingapi.resource.sessionvoting.request.CloseSessionVotingInput;
import com.ierddan.votingapi.resource.sessionvoting.request.CreateSessionVotingInput;
import com.ierddan.votingapi.entity.SessionVoting;
import com.ierddan.votingapi.service.SessionVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sessions-voting")
public class SessionVotingResource {

    @Autowired
    public final SessionVotingService service;

    public SessionVotingResource(SessionVotingService service) {
        this.service = service;
    }

    @PostMapping("/session-voting")
    public ResponseEntity<SessionVoting> createSessionVoting(@RequestBody CreateSessionVotingInput createSessionVotingInput) {
        SessionVoting sessionVoting = service.createSessionVoting(createSessionVotingInput.getIdVotingTheme());
        return ResponseEntity.ok(sessionVoting);
    }

    @GetMapping
    public ResponseEntity<List<SessionVoting>> getAllSessionVoting(){
        return ResponseEntity.ok(service.findAllSessionVoting());
    }

    @GetMapping("/session-voting/{idSessionVoting}")
    public ResponseEntity<SessionVoting> getSessionVotingId(@PathVariable Long idSessionVoting){
        return ResponseEntity.ok(service.findByIdSessionVoting(idSessionVoting));
    }

    @PutMapping("/session-voting/{idSessionVoting}")
    public ResponseEntity<SessionVoting> closeSessionVoting(CloseSessionVotingInput closeSessionVotingInput){
        return ResponseEntity.ok(service.closeSessionVoting(closeSessionVotingInput.getIdSessionVoting()));
    }


}

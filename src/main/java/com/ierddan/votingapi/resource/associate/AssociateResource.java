package com.ierddan.votingapi.resource.associate;

import com.ierddan.votingapi.resource.associate.response.AssociateOutput;
import com.ierddan.votingapi.entity.Associate;
import com.ierddan.votingapi.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/associates")
public class AssociateResource {

    @Autowired
    private final AssociateService service;

    public AssociateResource(AssociateService service) {
        this.service = service;
    }

    @GetMapping
    public List<AssociateOutput> getAllAssociates() {
        return service.getAllAssociates();
    }

    @PostMapping("/associate")
    public Associate saveAssociate(
            @RequestBody Associate associate) {
        return service.saveAssociate(associate);
    }

    @GetMapping("/associate/{id}")
    public AssociateOutput getAssociateById(@PathVariable Long id) {
        return service.findAssociateById(id);
    }

    @DeleteMapping("/associate/{id}")
    public void deleteAssociate(@PathVariable Long id) {
        service.deleteAssociate(id);
    }
}

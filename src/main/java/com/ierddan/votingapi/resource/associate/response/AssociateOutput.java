package com.ierddan.votingapi.resource.associate.response;

import com.ierddan.votingapi.client.response.AddressResponse;
import com.ierddan.votingapi.entity.Associate;
import lombok.Data;

@Data
public class AssociateOutput {

    public Long id;
    public String name;
    private String cpf;
    private String addressCode;
    private AddressResponse addressResponse;

    public AssociateOutput(Associate associate, AddressResponse address) {
        this.id = associate.getIdAssociate();
        this.name = associate.getName();
        this.cpf = associate.getCpf();
        this.addressCode = associate.getAddressCode();
        this.addressResponse = address;
    }

}

package com.ierddan.votingapi.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressResponse {

    @JsonProperty(value = "cep")
    private String addressCode;

    @JsonProperty(value = "logradouro")
    private String publicPlace;

    @JsonProperty(value = "complemento")
    private String complement;

    @JsonProperty(value = "bairro")
    private String neighborhood;

    @JsonProperty(value = "localidade")
    private String locality;

    @JsonProperty(value = "uf")
    private String state;
}

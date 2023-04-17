package com.ierddan.votingapi.client;

import com.ierddan.votingapi.client.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep-api", url = "viacep.com.br/ws")
public interface ViaCepApiClient {

    @GetMapping("/{addressCode}/json")
    ResponseEntity<AddressResponse> getAddres(@PathVariable String addressCode);
}

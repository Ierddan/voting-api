package com.ierddan.votingapi.service;

import com.ierddan.votingapi.client.ViaCepApiClient;
import com.ierddan.votingapi.client.response.AddressResponse;
import com.ierddan.votingapi.resource.associate.response.AssociateOutput;
import com.ierddan.votingapi.entity.Associate;
import com.ierddan.votingapi.repository.AssociateRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository repository;
    @Autowired
    private ViaCepApiClient viaCepClient;

    public AssociateService(AssociateRepository repository,ViaCepApiClient viaCepClient) {
        this.repository = repository;
        this.viaCepClient = viaCepClient;
    }

    public Associate saveAssociate(Associate associate) {
        try {
            if (repository.existsByCpf(associate.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado!");
            } else {
                return repository.save(associate);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o objeto Associate");
        }
    }

    public List<AssociateOutput> getAllAssociates() {
        try {
            List<Associate> listAssociates = repository.findAll();
            return listAssociates.stream()
                    .map(voter -> new AssociateOutput(voter, getAddress(voter.getAddressCode())))
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a lista de Associates");
        }
    }

    public AssociateOutput findAssociateById(Long id) {
        try {
            Associate associate = repository.findByIdAssociate(id);
            AddressResponse address = getAddress(associate.getAddressCode());
            return new AssociateOutput(associate, address);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    public Associate findAssociateByCpf(String cpf) {
        try {
            return repository.findByCpf(cpf);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    public AddressResponse getAddress(String addressCode) throws FeignException {
        try {
            return viaCepClient.getAddres(addressCode).getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação com a api viacep: " + e.getMessage());
        }
    }

    public void deleteAssociate(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }
}

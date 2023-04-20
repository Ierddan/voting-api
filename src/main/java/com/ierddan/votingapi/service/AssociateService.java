package com.ierddan.votingapi.service;

import com.ierddan.votingapi.client.ViaCepApiClient;
import com.ierddan.votingapi.client.response.AddressResponse;
import com.ierddan.votingapi.resource.associate.response.AssociateOutput;
import com.ierddan.votingapi.entity.Associate;
import com.ierddan.votingapi.repository.AssociateRepository;
import com.ierddan.votingapi.util.AssociateUtils;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ierddan.votingapi.util.AssociateUtils.*;

@Service
public class AssociateService {

    private final Logger logger = LoggerFactory.getLogger(AssociateService.class);

    private final AssociateRepository repository;

    private final ViaCepApiClient viaCepClient;

    @Autowired
    public AssociateService(AssociateRepository repository, ViaCepApiClient viaCepClient) {
        this.repository = repository;
        this.viaCepClient = viaCepClient;
    }

    public Associate saveAssociate(Associate associate) {
        logger.info("class=AssociateService method=saveAssociate associate=" + associate.toString());
        try {
            validateAssociate(associate);
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
        logger.info("class=AssociateService method=getAllAssociates");
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
        logger.info("class=AssociateService method=findAssociateById");
        try {
            Associate associate = repository.findByIdAssociate(id);
            AddressResponse address = getAddress(associate.getAddressCode());
            return new AssociateOutput(associate, address);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    public Associate findAssociateByCpf(String cpf) {
        logger.info("class=AssociateService method=findAssociateByCpf");
        try {
            return repository.findByCpf(cpf);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    public AddressResponse getAddress(String addressCode) throws FeignException {
        logger.info("class=AssociateService method=getAddress");
        try {
            return viaCepClient.getAddres(addressCode).getBody();
        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação com a api viacep: " + e.getMessage());
        }
    }

    public void deleteAssociate(Long id) {
        logger.info("class=AssociateService method=deleteAssociate");
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    public AssociateOutput updateAssociate(Associate associate) {
        logger.info("class=AssociateService method=updateAssociate associate=" + associate.toString());
        validateAssociate(associate);
        try {
            Associate currentAssociate = repository.findByIdAssociate(associate.getIdAssociate());
            currentAssociate.setName(associate.getName());
            currentAssociate.setCpf(associate.getCpf());
            currentAssociate.setAddressCode(associate.getAddressCode());
            AddressResponse address = getAddress(associate.getAddressCode());

            repository.save(currentAssociate);
            return new AssociateOutput(currentAssociate, address);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o objeto Associate");
        }
    }

    private void validateAssociate(Associate associate) {
        if (!validateCpf(associate.getCpf())) {
            throw new IllegalArgumentException("Número de CPF inválido!!");
        }
        if (!AssociateUtils.validateCep(associate.getAddressCode())) {
            throw new IllegalArgumentException("Número de CEP inválido!!");
        }
    }
}

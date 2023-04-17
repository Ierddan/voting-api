package com.ierddan.votingapi.repository;

import com.ierddan.votingapi.entity.Associate;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Associate findByIdAssociate(Long id);

    Associate findByCpf(String cpf);

    @Query("SELECT COUNT(v) > 0 FROM Associate v WHERE v.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);

}
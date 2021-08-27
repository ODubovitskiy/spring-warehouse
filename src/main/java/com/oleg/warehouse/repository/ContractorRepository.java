package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.ContractorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<ContractorEntity, Long> {
    boolean existsByInn(String inn);

    ContractorEntity findByName(String name);
}

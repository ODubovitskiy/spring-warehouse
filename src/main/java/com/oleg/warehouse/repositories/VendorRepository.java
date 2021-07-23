package com.oleg.warehouse.repositories;

import com.oleg.warehouse.entities.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    VendorEntity findByName(String name);

}

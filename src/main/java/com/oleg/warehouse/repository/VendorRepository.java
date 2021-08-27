package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    VendorEntity findByName(String name);

}

package com.oleg.warehouse.repository;

import com.oleg.warehouse.entity.ProductEntity;
import com.oleg.warehouse.entity.VendorEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findBySerialNumber(@NonNull String serialNumber);

    List<ProductEntity> findByVendor(@NonNull VendorEntity vendor);

    List<ProductEntity> findAllByDescriptionContaining(String filter);
}

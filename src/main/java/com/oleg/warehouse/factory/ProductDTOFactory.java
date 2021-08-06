package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.ProductDTO;
import com.oleg.warehouse.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOFactory {

    public ProductDTO makeDefault(ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .serialNumber(entity.getSerialNumber())
                .status(entity.getStatus())
                .vendor(entity.getVendor())
                .build();
    }
}
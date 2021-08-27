package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.ProductDTO;
import com.oleg.warehouse.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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


    public List<ProductDTO> makeProductDTOList(List<ProductEntity> list) {
        return list
                .stream()
                .map(this::makeDefault)
                .collect(Collectors.toList());
    }
}
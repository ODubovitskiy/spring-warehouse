package com.oleg.warehouse.dto;

import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.entities.VendorEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private Long id;
    private String description;
    private String serialNumber;
    private VendorEntity vendor;
    private Integer price;
    private String status;

    public static ProductDTO makeDefault(ProductEntity entity) {
        return builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .serialNumber(entity.getSerialNumber())
                .price(entity.getPrice())
                .status(entity.getStatus())
                .vendor(entity.getVendor())
                .build();
    }
}

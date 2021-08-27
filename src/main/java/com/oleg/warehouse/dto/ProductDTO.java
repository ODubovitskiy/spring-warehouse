package com.oleg.warehouse.dto;

import com.oleg.warehouse.entity.VendorEntity;
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

}

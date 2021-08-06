package com.oleg.warehouse.dto;


import com.oleg.warehouse.entities.VendorEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendorDTO {

    private Long id;
    private String name;
}

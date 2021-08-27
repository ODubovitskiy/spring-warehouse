package com.oleg.warehouse.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendorDTO {

    private Long id;
    private String name;
}

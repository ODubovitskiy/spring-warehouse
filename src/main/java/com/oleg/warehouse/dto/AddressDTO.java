package com.oleg.warehouse.dto;

import com.oleg.warehouse.entity.ContractorEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class AddressDTO {

    List<ContractorEntity> contractors;
    @NonNull
    private Long id;
    private String zip;
    private String country;
    private String city;
    private String street;
    private String building;

}

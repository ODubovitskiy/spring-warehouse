package com.oleg.warehouse.dto;

import com.oleg.warehouse.entity.ContractorEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WorkerDTO {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private ContractorEntity contractor;

}

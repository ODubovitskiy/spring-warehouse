package com.oleg.warehouse.dto;

import com.oleg.warehouse.entity.AddressEntity;
import com.oleg.warehouse.entity.WorkerEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ContractorDTO {
    private final Long id;
    private final String name;
    private final String inn;
    private final AddressEntity addressEntity;
    private final List<WorkerEntity> workers;
}

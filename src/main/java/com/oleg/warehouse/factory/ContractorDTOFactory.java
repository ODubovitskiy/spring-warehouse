package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.ContractorDTO;
import com.oleg.warehouse.entity.ContractorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractorDTOFactory {

    public final ContractorDTO makeDefault(ContractorEntity entity) {
        return ContractorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .inn(entity.getInn())
                .addressEntity(entity.getAddress())
                .workers(entity.getWorkers())
                .build();
    }

    public final List<ContractorDTO> makeContractorDTOList(List<ContractorEntity> list) {
        return list.stream()
                .map(this::makeDefault)
                .collect(Collectors.toList());
    }
}

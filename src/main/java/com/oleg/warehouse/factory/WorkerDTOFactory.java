package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.WorkerDTO;
import com.oleg.warehouse.entity.WorkerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkerDTOFactory {

    public WorkerDTO makeDefault(WorkerEntity entity) {
        return WorkerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDateOfBirth())
                .contractor(entity.getContractor())
                .build();
    }

    public List<WorkerDTO> makeWorkerDTOList(List<WorkerEntity> entities) {
        return entities.stream()
                .map(this::makeDefault)
                .collect(Collectors.toList());
    }
}

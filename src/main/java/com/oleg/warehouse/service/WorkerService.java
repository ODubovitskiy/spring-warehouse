package com.oleg.warehouse.service;

import com.oleg.warehouse.dto.WorkerDTO;
import com.oleg.warehouse.entity.ContractorEntity;
import com.oleg.warehouse.entity.WorkerEntity;
import com.oleg.warehouse.factory.WorkerDTOFactory;
import com.oleg.warehouse.repository.ContractorRepository;
import com.oleg.warehouse.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final ContractorRepository contractorRepository;
    private final WorkerDTOFactory workerDTOFactory;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, ContractorRepository contractorRepository, WorkerDTOFactory workerDTOFactory) {
        this.workerRepository = workerRepository;
        this.contractorRepository = contractorRepository;
        this.workerDTOFactory = workerDTOFactory;
    }


    public WorkerDTO store(WorkerEntity workerEntity) {
        ContractorEntity contractorEntity = contractorRepository.findByName(workerEntity.getContractor().getName());
        if (isWorkerExist(workerEntity))
            return workerDTOFactory.makeDefault(workerRepository.save(workerEntity));
        return null;
    }

    private boolean isWorkerExist(WorkerEntity workerEntity) {
        ExampleMatcher workerMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("name", ignoreCase())
                .withMatcher("lastName", ignoreCase());
        Example<WorkerEntity> check = Example.of(workerEntity, workerMatcher);
        return workerRepository.exists(check);
    }

}

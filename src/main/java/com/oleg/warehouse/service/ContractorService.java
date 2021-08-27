package com.oleg.warehouse.service;

import com.oleg.warehouse.dto.ContractorDTO;
import com.oleg.warehouse.entity.ContractorEntity;
import com.oleg.warehouse.exception.ContractorAlreadyExistsException;
import com.oleg.warehouse.exception.ContractorNotFoundException;
import com.oleg.warehouse.factory.ContractorDTOFactory;
import com.oleg.warehouse.repository.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {
    ContractorRepository contractorRepository;
    ContractorDTOFactory contractorDTOFactory;

    @Autowired
    public ContractorService(ContractorRepository contractorRepository, ContractorDTOFactory contractorDTOFactory) {
        this.contractorRepository = contractorRepository;
        this.contractorDTOFactory = contractorDTOFactory;
    }

    public List<ContractorDTO> index() {
        return contractorDTOFactory.makeContractorDTOList(contractorRepository.findAll());
    }

    public ContractorDTO store(ContractorEntity contractorEntity) throws ContractorAlreadyExistsException {
        if (contractorRepository.existsByInn(contractorEntity.getInn()))
            throw new ContractorAlreadyExistsException(String.format("Contractor with INN = %s already exists!",
                    contractorEntity.getInn()));
        return contractorDTOFactory.makeDefault(contractorRepository.save(contractorEntity));
    }

    public ContractorDTO show(Long id) throws ContractorNotFoundException {
        ContractorEntity contractorEntity = contractorRepository.findById(id).orElseThrow(() ->
                new ContractorNotFoundException(String.format("Contractor with id = %d doesn't exist!", id)));
        return contractorDTOFactory.makeDefault(contractorEntity);
    }

    public ContractorDTO update(Long id, ContractorEntity contractorEntity) {

        ContractorEntity contractorToUpdate = contractorRepository.findById(id).get();
        contractorToUpdate.setName(contractorEntity.getName());
        contractorToUpdate.setInn(contractorEntity.getInn());
        contractorToUpdate.setAddress(contractorEntity.getAddress());

        return contractorDTOFactory.makeDefault(contractorRepository.save(contractorToUpdate));
    }
}

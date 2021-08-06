package com.oleg.warehouse.services;

import com.oleg.warehouse.dto.VendorDTO;
import com.oleg.warehouse.entities.VendorEntity;
import com.oleg.warehouse.factory.VendorDTOFactory;
import com.oleg.warehouse.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private VendorDTOFactory vendorDTOFactory;


    @Autowired
    public VendorService(VendorRepository vendorRepository, VendorDTOFactory vendorDTOFactory) {
        this.vendorRepository = vendorRepository;
        this.vendorDTOFactory = vendorDTOFactory;

    }

    public VendorEntity getById(Long id) {
        return vendorRepository.findById(id).get();
    }

    public List<VendorDTO> getAll() {
        return vendorRepository.findAll().stream()
                .map(vendorDTOFactory::makeDefault)
                .collect(Collectors.toList());
    }
}

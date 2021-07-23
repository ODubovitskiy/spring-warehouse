package com.oleg.warehouse.services;

import com.oleg.warehouse.entities.VendorEntity;
import com.oleg.warehouse.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public VendorEntity getById(Long id) {
        return vendorRepository.findById(id).get();
    }

    public List<VendorEntity> getAll() {
        return vendorRepository.findAll();
    }
}

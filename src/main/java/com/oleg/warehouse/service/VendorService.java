package com.oleg.warehouse.service;

import com.oleg.warehouse.dto.VendorDTO;
import com.oleg.warehouse.factory.VendorDTOFactory;
import com.oleg.warehouse.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private VendorDTOFactory vendorDTOFactory;


    @Autowired
    public VendorService(VendorRepository vendorRepository, VendorDTOFactory vendorDTOFactory) {
        this.vendorRepository = vendorRepository;
        this.vendorDTOFactory = vendorDTOFactory;
    }

    public List<VendorDTO> getAll() {
        return vendorDTOFactory.makeVendorDTOList(vendorRepository.findAll());
    }
}

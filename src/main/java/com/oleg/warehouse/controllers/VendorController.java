package com.oleg.warehouse.controllers;

import com.oleg.warehouse.dto.VendorDTO;
import com.oleg.warehouse.factory.VendorDTOFactory;
import com.oleg.warehouse.services.VendorService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vendors")
@Data
@NoArgsConstructor
public class VendorController {

    public static final String GET_ALL = "";

    private VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<VendorDTO>> getAll() {
        return ResponseEntity.ok(vendorService.getAll());
    }
}

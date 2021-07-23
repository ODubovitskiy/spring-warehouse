package com.oleg.warehouse.controllers;

import com.oleg.warehouse.services.VendorService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendors")
@Data
@NoArgsConstructor
public class VendorController {

    private VendorService vendorService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(vendorService.getAll());
    }
}

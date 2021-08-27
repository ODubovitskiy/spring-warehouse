package com.oleg.warehouse.controller;

import com.oleg.warehouse.dto.AddressDTO;
import com.oleg.warehouse.entity.AddressEntity;
import com.oleg.warehouse.exception.AddressAlreadyExistsEsception;
import com.oleg.warehouse.exception.AddressNotFoundEException;
import com.oleg.warehouse.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final static String CREATE = "";
    private final static String GET_BY_ID = "/{id}";
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(CREATE)
    public ResponseEntity<AddressDTO> create(@RequestBody AddressEntity addressEntity) {
        try {
            return ResponseEntity.ok(addressService.save(addressEntity));
        } catch (AddressAlreadyExistsEsception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(addressService.getById(id));
        } catch (AddressNotFoundEException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}

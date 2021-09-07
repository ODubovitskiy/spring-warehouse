package com.oleg.warehouse.controller;

import com.oleg.warehouse.dto.ContractorDTO;
import com.oleg.warehouse.entity.ContractorEntity;
import com.oleg.warehouse.exception.ContractorAlreadyExistsException;
import com.oleg.warehouse.exception.ContractorNotFoundException;
import com.oleg.warehouse.exception.UnauthorisedException;
import com.oleg.warehouse.service.AuthService;
import com.oleg.warehouse.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/contractors")
public class ContractorController {

    public static final String INDEX = "";
    public static final String STORE = "";
    public static final String SHOW = "/{id}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";


    ContractorService contractorService;
    AuthService authService;

    @Autowired
    public ContractorController(ContractorService contractorService, AuthService authService) {
        this.contractorService = contractorService;
        this.authService = authService;
    }

    @GetMapping(INDEX)
    public ResponseEntity<List<ContractorDTO>> index(@RequestHeader String token) {
        try {
            authService.authenticate(token);
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return ResponseEntity.ok(contractorService.index());
    }

    @PostMapping(STORE)
    public ResponseEntity<ContractorDTO> store(@RequestBody ContractorEntity contractorEntity, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(contractorService.store(contractorEntity));
        } catch (ContractorAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping(SHOW)
    public ResponseEntity<ContractorDTO> show(@PathVariable Long id, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(contractorService.show(id));
        } catch (ContractorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PatchMapping(UPDATE)
    public ResponseEntity<ContractorDTO> update(@PathVariable Long id, @RequestBody ContractorEntity contractorEntity, @RequestHeader String token) {
        try {
            authService.authenticate(token);
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return ResponseEntity.ok(contractorService.update(id, contractorEntity));
    }
}

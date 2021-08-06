package com.oleg.warehouse.controllers;

import com.oleg.warehouse.dto.ProductDTO;
import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.exceptions.ProductAlreadyExistsException;
import com.oleg.warehouse.exceptions.ProductNotFoundException;
import com.oleg.warehouse.exceptions.VendorNotFoundException;
import com.oleg.warehouse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/products")
public class ProductController {

    public static final String GET_ALL = "";
    public static final String GET_BY_ID = "/{id}";
    public static final String CREATE = "";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
    public static final String FETCH_BY_VENDOR = "";
    public static final String FETCH_BY_DESCRIPTION = "";

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(GET_ALL)
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.getById(id));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(CREATE)
    public ResponseEntity<ProductDTO> save(@RequestBody ProductEntity productEntity) {
        try {
            return ResponseEntity.ok(productService.saveProduct(productEntity));
        } catch (ProductAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (VendorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping(UPDATE)
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id,
                                             @RequestBody ProductEntity productEntity) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productEntity));
        } catch (ProductNotFoundException | VendorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping({DELETE})
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(productService.delete(id));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = FETCH_BY_DESCRIPTION, params = "description")
    public ResponseEntity<List<ProductDTO>> getByFilter(@RequestParam String description) {
        return ResponseEntity.ok(productService.fetchByDescription(description.toLowerCase(Locale.ROOT)));
    }

    @GetMapping(value = FETCH_BY_VENDOR, params = "vendor")
    public ResponseEntity<List<ProductDTO>> getByVendor(@RequestParam String vendor) {
        try {
            return ResponseEntity.ok(productService.fetchByVendor(vendor));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
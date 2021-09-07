package com.oleg.warehouse.controller;

import com.oleg.warehouse.dto.ProductDTO;
import com.oleg.warehouse.entity.ProductEntity;
import com.oleg.warehouse.exception.ProductAlreadyExistsException;
import com.oleg.warehouse.exception.ProductNotFoundException;
import com.oleg.warehouse.exception.UnauthorisedException;
import com.oleg.warehouse.exception.VendorNotFoundException;
import com.oleg.warehouse.service.AuthService;
import com.oleg.warehouse.service.ProductService;
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

    public static final String INDEX = "";
    public static final String STORE = "";
    public static final String SHOW = "/{id}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
    public static final String FETCH_BY_VENDOR = "";
    public static final String FETCH_BY_DESCRIPTION = "";

    private final ProductService productService;
    private final AuthService authService;

    @Autowired
    public ProductController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    @GetMapping(INDEX)
    public List<ProductDTO> index(@RequestHeader String token) {
        try {
            authService.authenticate(token);
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return productService.getAll();
    }

    @GetMapping(SHOW)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.getById(id));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping(STORE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductEntity productEntity, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.saveProduct(productEntity));
        } catch (ProductAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (VendorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PatchMapping(UPDATE)
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @RequestBody ProductEntity productEntity, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.updateProduct(id, productEntity));
        } catch (ProductNotFoundException | VendorNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping({DELETE})
    public ResponseEntity<Long> delete(@PathVariable("id") Long id, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.delete(id));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping(value = FETCH_BY_DESCRIPTION, params = "description")
    public ResponseEntity<List<ProductDTO>> getByFilter(@RequestParam String description, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.fetchByDescription(description.toLowerCase(Locale.ROOT)));
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping(value = FETCH_BY_VENDOR, params = "vendor")
    public ResponseEntity<List<ProductDTO>> getByVendor(@RequestParam String vendor, @RequestHeader String token) {
        try {
            authService.authenticate(token);
            return ResponseEntity.ok(productService.fetchByVendor(vendor));
        } catch (ProductNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorisedException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
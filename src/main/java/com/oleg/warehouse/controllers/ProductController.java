package com.oleg.warehouse.controllers;

import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.exceptions.ProductAlreadyExistsException;
import com.oleg.warehouse.exceptions.ProductNotFoundException;
import com.oleg.warehouse.services.ProductService;
import com.oleg.warehouse.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final VendorService vendorService;

    @Autowired
    public ProductController(ProductService productService, VendorService vendorService) {
        this.productService = productService;
        this.vendorService = vendorService;
    }

    @GetMapping(path = "/all")
    public List<ProductEntity> getAll() {
        return productService.getAll();
    }

    @GetMapping()
    public ResponseEntity getProductById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productService.getById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "")
    public ResponseEntity save(@RequestBody ProductEntity productEntity) {
        try {
            productService.saveProduct(productEntity);
            return ResponseEntity.ok("Product has been stored");
        } catch (ProductAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestBody ProductEntity productEntity) {
        try {
            productService.updateProduct(id, productEntity);
            return ResponseEntity.ok("Product has been updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(productService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(params = "vendor")
    public ResponseEntity getByVendor(@RequestParam String vendor) {
        try {
            return ResponseEntity.ok(productService.getByVendor(vendor));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

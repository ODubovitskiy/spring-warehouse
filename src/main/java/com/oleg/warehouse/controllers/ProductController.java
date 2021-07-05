package com.oleg.warehouse.controllers;

import com.oleg.warehouse.models.Product;
import com.oleg.warehouse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(path = "{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PostMapping(path = "")
    public Product save(@RequestParam String description,
                        @RequestParam String serialNumber,
                        @RequestParam String vendor,
                        @RequestParam String price,
                        @RequestParam String status) {
        Product product = new Product();
        product.setDescription(description);
        product.setSerialNumber(serialNumber);
        product.setVendor(vendor);
        product.setPrice(Integer.parseInt(price));
        product.setStatus(status);
        return productService.save(product);
    }

    @PatchMapping("{id}")
    public Product update(@PathVariable("id") Long id,
                          @RequestParam String description,
                          @RequestParam String serialNumber,
                          @RequestParam String vendor,
                          @RequestParam String price,
                          @RequestParam String status) {
        Product productToBeUpdated = productService.getById(id);
        productToBeUpdated.setId(id);
        productToBeUpdated.setDescription(description);
        productToBeUpdated.setSerialNumber(serialNumber);
        productToBeUpdated.setVendor(vendor);
        productToBeUpdated.setPrice(Integer.parseInt(price));
        productToBeUpdated.setStatus(status);
        return productService.save(productToBeUpdated);
    }

    @DeleteMapping({"{id}"})
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}

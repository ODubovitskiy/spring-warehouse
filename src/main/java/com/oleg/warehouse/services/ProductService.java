package com.oleg.warehouse.services;

import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.entities.VendorEntity;
import com.oleg.warehouse.exceptions.ProductAlreadyExistsException;
import com.oleg.warehouse.exceptions.ProductNotFoundException;
import com.oleg.warehouse.models.Product;
import com.oleg.warehouse.repositories.ProductRepository;
import com.oleg.warehouse.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAllByOrderByIdAsc();
    }

    public Product getById(Long id) throws ProductNotFoundException {
        try {
            ProductEntity existingProduct = productRepository.findById(id).get();
            return Product.getModel(existingProduct);
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException("Product with id = " + id + " does not exist!");
        }
    }

    public ProductEntity saveProduct(ProductEntity product) throws ProductAlreadyExistsException {
        VendorEntity vendor = vendorRepository.findByName(product.getVendor().getName());
        product.setVendor(vendor);
        List<ProductEntity> products = productRepository.findBySerialNumber(product.getSerialNumber());
        if (products.size() > 0) {
            if (products.contains(product))
                throw new ProductAlreadyExistsException("Product already exists");
        }
        return productRepository.save(product);
    }

    public Long delete(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    public List<ProductEntity> getByVendor(String vendor) throws ProductNotFoundException {
        VendorEntity vendorObj = new VendorEntity(vendor);
        List<ProductEntity> products = productRepository.findByVendor(vendorObj);
        if (products.size() > 0) {
            return products;
        } else {
            throw new ProductNotFoundException("There are no products produced by " + vendor);
        }
    }

    public ProductEntity updateProduct(Long id, ProductEntity productDTO) {

        ProductEntity productToUpdate = productRepository.getById(id);
        VendorEntity vendor = vendorRepository.findByName(productDTO.getVendor().getName());

        productToUpdate.setDescription(productDTO.getDescription());
        productToUpdate.setSerialNumber(productDTO.getSerialNumber());
        productToUpdate.setVendor(vendor);
        productToUpdate.setPrice(productDTO.getPrice());
        productToUpdate.setStatus(productDTO.getStatus());
        return productRepository.save(productToUpdate);
    }
}

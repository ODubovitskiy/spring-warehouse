package com.oleg.warehouse.services;

import com.oleg.warehouse.dto.ProductDTO;
import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.entities.VendorEntity;
import com.oleg.warehouse.exceptions.ProductAlreadyExistsException;
import com.oleg.warehouse.exceptions.ProductNotFoundException;
import com.oleg.warehouse.exceptions.VendorNotFoundException;
import com.oleg.warehouse.factory.ProductDTOFactory;
import com.oleg.warehouse.repositories.ProductRepository;
import com.oleg.warehouse.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ProductDTOFactory productDTOFactory;

    @Autowired
    public ProductService(ProductRepository productRepository, VendorRepository vendorRepository, ProductDTOFactory productDTOFactory) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
        this.productDTOFactory = productDTOFactory;
    }

    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream()
                .map(productDTOFactory::makeDefault)
                .collect(Collectors.toList());
    }

    public ProductDTO getById(Long id) throws ProductNotFoundException {
        if (!productRepository.existsById(id))
            throw new ProductNotFoundException("Product with id = " + id + " does not exist!");
        return productDTOFactory.makeDefault(productRepository.findById(id).get());
    }

    public ProductDTO saveProduct(ProductEntity productEntity) throws ProductAlreadyExistsException, VendorNotFoundException {
        VendorEntity vendor = vendorRepository.findByName(productEntity.getVendor().getName());
        if (vendor == null)
            throw new VendorNotFoundException(String.format("Vendor %s doesn't exist", productEntity.getVendor().getName()));
        productEntity.setVendor(vendor);
        List<ProductEntity> products = productRepository.findBySerialNumber(productEntity.getSerialNumber());
        if (products.contains(productEntity))
            throw new ProductAlreadyExistsException("Product already exists");
        return productDTOFactory.makeDefault(productRepository.save(productEntity));
    }

    public Long delete(Long id) throws ProductNotFoundException {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return id;
        } else {
            throw new ProductNotFoundException("Product with id = " + id + " does not exist!");
        }
    }

    public ProductDTO updateProduct(Long id, ProductEntity productEntity) throws ProductNotFoundException, VendorNotFoundException {

        ProductEntity productToUpdate = productRepository.getById(id);
        VendorEntity vendor = vendorRepository.findByName(productEntity.getVendor().getName());
        if (vendor == null)
            throw new VendorNotFoundException(String.format("Vendor %s doesn't exist", productEntity.getVendor().getName()));

        productToUpdate.setDescription(productEntity.getDescription());
        productToUpdate.setSerialNumber(productEntity.getSerialNumber());
        productToUpdate.setVendor(vendor);
        productToUpdate.setPrice(productEntity.getPrice());
        productToUpdate.setStatus(productEntity.getStatus());

        return productDTOFactory.makeDefault(productRepository.save(productToUpdate));
    }

    public List<ProductDTO> fetchByDescription(String description) {
        return productRepository.findAllByDescriptionContaining(description).stream()
                .map(productDTOFactory::makeDefault)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> fetchByVendor(String vendor) throws ProductNotFoundException {
        VendorEntity vendorObj = vendorRepository.findByName(vendor);
        List<ProductEntity> products = productRepository.findByVendor(vendorObj);
        if (products.size() > 0) {
            return products.stream()
                    .map(productDTOFactory::makeDefault)
                    .collect(Collectors.toList());
        } else {
            throw new ProductNotFoundException("There are no products produced by " + vendor);
        }
    }
}

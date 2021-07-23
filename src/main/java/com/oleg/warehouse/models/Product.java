package com.oleg.warehouse.models;

import com.oleg.warehouse.entities.ProductEntity;
import com.oleg.warehouse.entities.VendorEntity;
import lombok.Data;

@Data
public class Product {

    private Long id;
    private String description;
    private String serialNumber;
    private VendorEntity vendor;
    private Integer price;
    private String status;

    public static Product getModel(ProductEntity productEntity) {
        Product product = new Product();

        product.setId(productEntity.getId());
        product.setDescription(productEntity.getDescription());
        product.setSerialNumber(productEntity.getSerialNumber());
        product.setVendor(productEntity.getVendor());
        product.setPrice(productEntity.getPrice());
        product.setStatus(productEntity.getStatus());

        return product;

    }
}

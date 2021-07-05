package com.oleg.warehouse.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String errMessage) {
        super(errMessage);
    }
}

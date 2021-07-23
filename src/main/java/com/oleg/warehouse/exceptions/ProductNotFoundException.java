package com.oleg.warehouse.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String errMessage) {
        super(errMessage);
    }
}

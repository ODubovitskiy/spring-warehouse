package com.oleg.warehouse.exception;

import javassist.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String errMessage) {
        super(errMessage);
    }
}

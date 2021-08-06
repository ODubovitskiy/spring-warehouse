package com.oleg.warehouse.exceptions;

import javassist.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String errMessage) {
        super(errMessage);
    }
}

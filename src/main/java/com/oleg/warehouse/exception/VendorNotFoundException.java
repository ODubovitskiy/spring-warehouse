package com.oleg.warehouse.exception;

import javassist.NotFoundException;

public class VendorNotFoundException extends NotFoundException {

    public VendorNotFoundException(String msg) {
        super(msg);
    }
}

package com.oleg.warehouse.exceptions;

import javassist.NotFoundException;

public class VendorNotFoundException extends NotFoundException {

    public VendorNotFoundException(String msg) {
        super(msg);
    }
}

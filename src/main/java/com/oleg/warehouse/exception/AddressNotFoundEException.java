package com.oleg.warehouse.exception;

import javassist.NotFoundException;

public class AddressNotFoundEException extends NotFoundException {
    public AddressNotFoundEException(String msg) {
        super(msg);
    }
}

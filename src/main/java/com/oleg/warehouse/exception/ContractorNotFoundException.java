package com.oleg.warehouse.exception;

import javassist.NotFoundException;

public class ContractorNotFoundException extends NotFoundException {
    public ContractorNotFoundException(String message) {
        super(message);
    }
}

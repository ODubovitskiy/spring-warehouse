package com.oleg.warehouse.exception;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}

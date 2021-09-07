package com.oleg.warehouse.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@UtilityClass
public class StringChecker {

    public void checkFieldStringOnEmptiness(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Field '%s' cannot be empty", fieldName));
    }
}

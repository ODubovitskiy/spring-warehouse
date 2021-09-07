package com.oleg.warehouse.util;

import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.UUID;

@UtilityClass
public class UserCredentialsCreator {

    public String createPassword() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }

    /**
     * For the sake of simplicity, suppose there won't be Jane & John Smith :)
     */
    public String createLogin(String firstName, String lastName) {
        return String.format("%s.%s", firstName.toLowerCase(Locale.ROOT).charAt(0), lastName.toLowerCase(Locale.ROOT));
    }
}

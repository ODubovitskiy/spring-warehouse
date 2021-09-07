package com.oleg.warehouse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
}

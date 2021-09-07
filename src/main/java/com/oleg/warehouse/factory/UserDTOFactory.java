package com.oleg.warehouse.factory;

import com.oleg.warehouse.dto.UserDTO;
import com.oleg.warehouse.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDTOFactory {

    public UserDTO makeDefault(UserEntity entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .login(entity.getLogin())
                .build();
    }
}

package com.oleg.warehouse.controller;

import com.oleg.warehouse.dto.UserDTO;
import com.oleg.warehouse.entity.UserEntity;
import com.oleg.warehouse.exception.UserAlreadyExistsException;
import com.oleg.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/users")
public class UserController {

    public static final String STORE = "";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(STORE)
    public ResponseEntity<UserDTO> store(@RequestBody UserEntity userEntity) {
        try {

            return ResponseEntity.ok(userService.store(userEntity));
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}






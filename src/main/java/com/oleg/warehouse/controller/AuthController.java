package com.oleg.warehouse.controller;

import com.oleg.warehouse.entity.UserEntity;
import com.oleg.warehouse.exception.UserNotExistsException;
import com.oleg.warehouse.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    public static final String LOGIN = "/login";
    AuthService authorize;

    public AuthController(AuthService authorize) {
        this.authorize = authorize;
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        try {
            return ResponseEntity.ok(authorize.authorize(userEntity.getLogin(), userEntity.getPassword()));
        } catch (UserNotExistsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

package com.oleg.warehouse.service;

import com.oleg.warehouse.entity.TokenEntity;
import com.oleg.warehouse.exception.UnauthorisedException;
import com.oleg.warehouse.exception.UserNotExistsException;
import com.oleg.warehouse.repository.TokenRepository;
import com.oleg.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public String authorize(String login, String password) throws UserNotExistsException {
        userRepository.findByLoginAndPassword(login, password)
                .orElseThrow(() -> new UserNotExistsException(String.format("User with login '%s' doesn't exist", login)));

        TokenEntity token = tokenRepository.save(TokenEntity.builder().build());
        return token.getToken();
    }

    public void authenticate(String token) throws UnauthorisedException {
        TokenEntity tokenEntity = tokenRepository.findById(token)
                .orElseThrow(() -> new UnauthorisedException("You must enter the system to proceed"));
        if (tokenEntity.getExpireAt().isBefore(LocalTime.now())) {
            throw new UnauthorisedException("Your token is expired. You must enter the system to keep using it.");
        }
    }
}

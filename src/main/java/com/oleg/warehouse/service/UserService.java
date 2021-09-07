package com.oleg.warehouse.service;

import com.oleg.warehouse.dto.UserDTO;
import com.oleg.warehouse.entity.UserEntity;
import com.oleg.warehouse.exception.UserAlreadyExistsException;
import com.oleg.warehouse.factory.UserDTOFactory;
import com.oleg.warehouse.repository.TokenRepository;
import com.oleg.warehouse.repository.UserRepository;
import com.oleg.warehouse.util.StringChecker;
import com.oleg.warehouse.util.UserCredentialsCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOFactory userDTOFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOFactory userDTOFactory) {
        this.userRepository = userRepository;
        this.userDTOFactory = userDTOFactory;
    }

    public UserDTO store(UserEntity userEntity) throws UserAlreadyExistsException {
        String firstName = userEntity.getFirstName();
        String lastName = userEntity.getLastName();
        StringChecker.checkFieldStringOnEmptiness(firstName, "first name");
        StringChecker.checkFieldStringOnEmptiness(lastName, "last name");
        //Check middleName separately as it is optional
        String middleName = userEntity.getMiddleName().trim();
        middleName = middleName.isEmpty() ? null : middleName;

        String login = UserCredentialsCreator.createLogin(firstName, lastName);
        String password = UserCredentialsCreator.createPassword();

        userEntity.setMiddleName(middleName);
        userEntity.setLogin(login);
        userEntity.setPassword(password);

        if (!userRepository.existsByLogin(login))
            return userDTOFactory.makeDefault(userRepository.save(userEntity));
        else
            throw new UserAlreadyExistsException(String.format("User with login '%s' already exists.", login));
    }
}

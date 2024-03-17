package org.example.marketplaceapi.service;


import org.example.marketplaceapi.converter.UserConverter;
import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTOView loginUser(UserDTOForm userDTOForm) {
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null");
        //check if email already exists
        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (!isExistEmail) {
            return null;
        }
        User existingUser = userRepository.findByEmail(userDTOForm.getEmail()).get();
        return userConverter.UserToUserDTOView(existingUser);
    }

        @Override
    public UserDTOView signupUser(UserDTOForm userDTOForm) {
        //check params
        if (userDTOForm == null) throw new IllegalArgumentException("User form is null");
        //check if email already exists
        boolean isExistEmail = userRepository.existsByEmail(userDTOForm.getEmail());
        if (isExistEmail) {
            return null;
        }

        //Hash password
        User newUser = userConverter.UserDTOFormToUser(userDTOForm);
        String encodedPassword = passwordEncoder.encode(userDTOForm.getPassword());
        if (encodedPassword == null) {
            throw new RuntimeException("Password Encryption failed...");
        }
        newUser.setPassword(encodedPassword);

        //Create a new user entity and save it to database
        User saveUser = userRepository.save(newUser);
        //Convert saved user (entity) to dto view

        return userConverter.UserToUserDTOView(saveUser);
    }


}



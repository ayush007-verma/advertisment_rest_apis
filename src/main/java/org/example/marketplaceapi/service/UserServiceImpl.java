package org.example.marketplaceapi.service;


import org.example.marketplaceapi.converter.UserConverter;
import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Autowired

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            throw new RuntimeException("User already exists. Please login");
        }

        //Hash password
        User newUser = userConverter.UserDTOFormToUser(userDTOForm);
        newUser.setPassword(passwordEncoder.encode(userDTOForm.getPassword()));

        //Create a new user entity and save it to database
        User saveUser = userRepository.save(newUser);
        //Convert saved user (entity) to dto view

        return userConverter.UserToUserDTOView(saveUser);
    }


}



package org.example.marketplaceapi.service;


import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    @Autowired

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTOView loginSignupUser(UserDTOForm userDTOForm) {

        Optional<User> user = userRepository.findByEmail(userDTOForm.getEmail());
        if (user.isPresent()) {


            return new UserDTOView(user.get().getId(),user.get().getUserName(),user.get().getEmail()) ;



        } else {
             User newUser = User.fromDTO(userDTOForm);
             User savedUser = userRepository.save(newUser);
             return new UserDTOView(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail());


        }



    }
}

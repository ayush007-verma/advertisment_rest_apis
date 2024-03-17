package org.example.marketplaceapi.controller;


import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.example.marketplaceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth/")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTOView> loginUser(UserDTOForm userDTOForm) {
        UserDTOView loggedInUser = userService.loginUser(userDTOForm);
        if (loggedInUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public UserDTOView signupUser(UserDTOForm userDTOForm) {
        UserDTOView signedUpUser = userService.signupUser(userDTOForm);
        return signedUpUser;
    }

}
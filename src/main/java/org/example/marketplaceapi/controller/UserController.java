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
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/auth")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTOForm userDTOForm) {
        UserDTOView loggedInUser = userService.loginUser(userDTOForm);
        if (loggedInUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully logged in as " + loggedInUser.getUsername(), HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signupUser(@RequestBody UserDTOForm userDTOForm) {
        UserDTOView signedUpUser = userService.signupUser(userDTOForm);
        if (signedUpUser == null) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully registered new user as " + signedUpUser.getUsername(), HttpStatus.OK);
    }

}

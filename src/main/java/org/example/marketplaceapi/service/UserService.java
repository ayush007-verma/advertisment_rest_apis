package org.example.marketplaceapi.service;

import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;

public interface UserService {
    UserDTOView loginUser(UserDTOForm userDTOForm);
    UserDTOView signupUser(UserDTOForm userDTOForm);
}

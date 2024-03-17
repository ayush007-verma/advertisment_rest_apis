package org.example.marketplaceapi.converter;

import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements UserConverter {
    @Override
    public UserDTOView UserToUserDTOView(User user) {
        UserDTOView userDTOView = new UserDTOView();
        userDTOView.setEmail(user.getEmail());
        userDTOView.setUsername(user.getUserName());
        return userDTOView;
    }

    @Override
    public User UserDTOViewToUser(UserDTOView userDTOView) {
        User user = new User();
        return user;
    }

    @Override
    public UserDTOForm UserToUserDTOForm(User entity) {
        UserDTOForm userDTOForm = new UserDTOForm();
        return userDTOForm;
    }

    @Override
    public User UserDTOFormToUser(UserDTOForm userDTOForm) {
        User user = new User();
        user.setUserName(userDTOForm.getUsername());
        user.setEmail(userDTOForm.getEmail());
        return user;
    }
}
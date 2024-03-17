package org.example.marketplaceapi.converter;

import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.model.dto.UserDTOForm;
import org.example.marketplaceapi.model.dto.UserDTOView;
import org.springframework.stereotype.Component;

@Component
public interface UserConverter {
    UserDTOView UserToUserDTOView(User entity);

    User UserDTOViewToUser(UserDTOView dtoView);

    UserDTOForm UserToUserDTOForm(User entity);

    User UserDTOFormToUser(UserDTOForm dtoView);
}

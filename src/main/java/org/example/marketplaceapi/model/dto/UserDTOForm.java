package org.example.marketplaceapi.model.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDTOForm implements Serializable {

    private String username;
    private String email;
    private String password;
}

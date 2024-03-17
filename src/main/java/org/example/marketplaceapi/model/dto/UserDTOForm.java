package org.example.marketplaceapi.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDTOForm {

    private String username;
    private String email;
    private String password;



}

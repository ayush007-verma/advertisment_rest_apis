package org.example.marketplaceapi.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDTOView {

    private Long id;
    private String username;
    private String email;

}

package org.example.marketplaceapi.model.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDTOView implements Serializable {

    private String username;
    private String email;
}

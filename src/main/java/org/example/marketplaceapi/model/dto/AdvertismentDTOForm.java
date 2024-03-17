package org.example.marketplaceapi.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdvertismentDTOForm {
    private String title;
    private String description;
    private String email;
    private LocalDate expirationDate;
}
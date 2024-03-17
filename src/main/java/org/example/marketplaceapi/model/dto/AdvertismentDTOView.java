package org.example.marketplaceapi.model.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvertismentDTOView {
    private String title;
    private String description;
    private LocalDate publicationDate;
    private LocalDate expirationDate;
    private String email;
}

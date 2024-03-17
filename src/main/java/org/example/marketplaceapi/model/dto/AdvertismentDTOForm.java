package org.example.marketplaceapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expirationDate;
}
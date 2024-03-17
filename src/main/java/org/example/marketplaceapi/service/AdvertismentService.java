package org.example.marketplaceapi.service;

import org.example.marketplaceapi.model.dto.AdvertismentDTOForm;
import org.example.marketplaceapi.model.dto.AdvertismentDTOView;

import java.util.List;

public interface AdvertismentService {

    AdvertismentDTOView saveAdvertisement(AdvertismentDTOForm advertismentDTOForm);

    List<AdvertismentDTOView> listAdvertisments(String email);

}

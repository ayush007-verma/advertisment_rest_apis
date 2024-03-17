package org.example.marketplaceapi.converter;

import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.model.Entity.Advertisment;
import org.example.marketplaceapi.model.dto.AdvertismentDTOForm;
import org.example.marketplaceapi.model.dto.AdvertismentDTOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementConverterImpl implements  AdvertisementConverter {

    public Advertisment advertisementDTOFormToAdvertisement(AdvertismentDTOForm advertismentDTOForm) {
        Advertisment advertisment = new Advertisment();
        advertisment.setDescription(advertismentDTOForm.getDescription());
        advertisment.setTitle(advertismentDTOForm.getTitle());
        advertisment.setExpirationDate(advertismentDTOForm.getExpirationDate());
        return  advertisment;
    }

    public AdvertismentDTOView advertismenttoAdvertisementDTOView(Advertisment advertisment) {
        AdvertismentDTOView advertismentDTOView = new AdvertismentDTOView();
        advertismentDTOView.setTitle(advertisment.getTitle());
        advertismentDTOView.setDescription(advertisment.getDescription());
        advertismentDTOView.setExpirationDate(advertisment.getExpirationDate());
        advertismentDTOView.setPublicationDate(advertisment.getPublicationDate());
        if (advertisment != null && advertisment.getUser() != null ) {
            advertismentDTOView.setEmail(advertisment.getUser().getEmail());
        }
        return advertismentDTOView;
    }
}

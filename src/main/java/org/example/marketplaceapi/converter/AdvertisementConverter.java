package org.example.marketplaceapi.converter;

import org.example.marketplaceapi.model.Entity.Advertisment;
import org.example.marketplaceapi.model.dto.AdvertismentDTOForm;
import org.example.marketplaceapi.model.dto.AdvertismentDTOView;

public interface AdvertisementConverter {
    Advertisment advertisementDTOFormToAdvertisement(AdvertismentDTOForm advertismentDTOForm);
    AdvertismentDTOView advertismenttoAdvertisementDTOView(Advertisment advertisment);
}

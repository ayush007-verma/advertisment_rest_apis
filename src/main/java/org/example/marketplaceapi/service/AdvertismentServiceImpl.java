package org.example.marketplaceapi.service;

import org.example.marketplaceapi.Repository.AdvertismentRepository;
import org.example.marketplaceapi.Repository.UserRepository;
import org.example.marketplaceapi.converter.AdvertisementConverter;
import org.example.marketplaceapi.model.Entity.Advertisment;
import org.example.marketplaceapi.model.Entity.User;
import org.example.marketplaceapi.model.dto.AdvertismentDTOForm;
import org.example.marketplaceapi.model.dto.AdvertismentDTOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertismentServiceImpl implements AdvertismentService {

    private final UserRepository userRepository;
    private final AdvertismentRepository advertismentRepository;
    private final AdvertisementConverter advertisementConverter;

    @Autowired
    public AdvertismentServiceImpl(UserRepository userRepository, AdvertismentRepository advertismentRepository, PasswordEncoder passwordEncoder, AdvertisementConverter advertisementConverter) {
        this.userRepository = userRepository;
        this.advertismentRepository = advertismentRepository;
        this.advertisementConverter = advertisementConverter;
//        this.passwordEncoder = passwordEncoder;
    }


    public AdvertismentDTOView saveAdvertisement(AdvertismentDTOForm advertismentDTOForm) {
        User currentUser = userRepository.findByEmail(advertismentDTOForm.getEmail()).get();
        if (currentUser == null) {
            throw new RuntimeException("No user exists for this Email");
        }


        if(advertismentRepository.findByTitle(advertismentDTOForm.getTitle()).isPresent()) {
            System.out.println("Advertisment with the same title is already present...");
            return null;
        }

        Advertisment advertisement = advertisementConverter.advertisementDTOFormToAdvertisement(advertismentDTOForm);


        AdvertismentDTOView advertismentDTO = advertisementConverter.advertismenttoAdvertisementDTOView(advertismentRepository.save(advertisement));
        return advertismentDTO;
    }

    @Override
    public List<AdvertismentDTOView> listAdvertisments(String email) {
        User currentUser = userRepository.findByEmail(email).get();
        List<Advertisment> advertismentList = new ArrayList<>();
        if (email == null) {
            advertismentList = advertismentRepository.findAll();

            advertismentList = advertismentList.stream()
                    .filter(advertisement -> advertisement.getExpirationDate().isAfter(LocalDate.now()))
                    .collect(Collectors.toList());
        } else {
            advertismentList = advertismentRepository.findByUser(currentUser);
        }

        return advertismentList
                        .stream()
                        .map(advertisementConverter::advertismenttoAdvertisementDTOView).toList();
    }
}

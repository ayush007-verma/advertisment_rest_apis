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
import java.util.Optional;
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
        if (advertismentDTOForm == null || advertismentDTOForm.getEmail() == null) {
            throw new RuntimeException("email or dto is null for advertisement save request....");
        }
        User currentUser = userRepository.findByEmail(advertismentDTOForm.getEmail()).get();
        if (currentUser == null) {
            throw new RuntimeException("No user exists for this Email");
        }


        if(advertismentRepository.findByTitle(advertismentDTOForm.getTitle()).isPresent()) {
            System.out.println("Advertisment with the same title is already present...");
            return null;
        }

        Advertisment advertisement = advertisementConverter.advertisementDTOFormToAdvertisement(advertismentDTOForm);

        advertisement.setUser(currentUser);
        System.out.println(advertisement.getUser() == null ? "user is null" : "user id : " + String.valueOf(advertisement.getUser().getId()));

        AdvertismentDTOView advertismentDTO = advertisementConverter.advertismenttoAdvertisementDTOView(advertismentRepository.save(advertisement));
        return advertismentDTO;
    }

    @Override
    public List<AdvertismentDTOView> listAdvertisments(String email) {
        List<Advertisment> advertismentList = new ArrayList<>();
        if (email == null) {
            // checking for list of advertisements all....
            advertismentList = advertismentRepository.findAll();
            // expiration date filter below if it is passed then filter those advertisements from result...
            advertismentList = advertismentList.stream()
                    .filter(advertisement -> advertisement.getExpirationDate().isAfter(LocalDate.now()))
                    .collect(Collectors.toList());
        } else {
            // checking for list of advertisements for a particular user ..
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                throw new RuntimeException("No user creds found for email : " + email);
            }
            User currentUser = user.get();
            advertismentList = advertismentRepository.findByUser(currentUser);
        }

        return advertismentList
                        .stream()
                        .map(advertisementConverter::advertismenttoAdvertisementDTOView).toList();
    }
}

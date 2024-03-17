package org.example.marketplaceapi.controller;

import org.example.marketplaceapi.model.dto.AdvertismentDTOForm;
import org.example.marketplaceapi.model.dto.AdvertismentDTOView;
import org.example.marketplaceapi.service.AdvertismentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/advertisment")
public class AdvertisementController {
    private AdvertismentService advertismentService;

    @PostMapping("/save")
    public ResponseEntity<String> saveAdvertisement(@RequestBody AdvertismentDTOForm advertismentDTOForm) {

        AdvertismentDTOView advertismentDTO = advertismentService.saveAdvertisement(null);

        if (advertismentDTO == null) {
            return new ResponseEntity<>("Advertisment with the same title is already present...", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Saved Advertisement for user :", HttpStatus.OK);
    }

    @GetMapping("/list")
    public  ResponseEntity<List<AdvertismentDTOView>> listAdvertisements() {

        List<AdvertismentDTOView> advertismentDTOList  = advertismentService.listAdvertisments(null);

        return new ResponseEntity<>(advertismentDTOList, HttpStatus.OK);
    }

    @GetMapping("/list/user")
    public  ResponseEntity<List<AdvertismentDTOView>> listAdvertisementsForUser(@RequestParam String email) {

        List<AdvertismentDTOView> advertismentDTOList  = advertismentService.listAdvertisments(email);

        return new ResponseEntity<>(advertismentDTOList, HttpStatus.OK);
    }

}

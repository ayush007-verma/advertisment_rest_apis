package org.example.marketplaceapi.Repository;

import org.example.marketplaceapi.model.Entity.Advertisment;
import org.example.marketplaceapi.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertismentRepository extends JpaRepository<Advertisment, Long> {

   List<Advertisment> findByExpirationDate(LocalDate expirationDate);

   Optional<Advertisment> findByTitle(String title);

   List<Advertisment> findByUser(User user);
}
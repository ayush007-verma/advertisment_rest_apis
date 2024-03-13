package org.example.marketplaceapi.Repository;

import org.example.marketplaceapi.Entity.Advertisment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertismentRepository extends JpaRepository <Advertisment,Long > {
}

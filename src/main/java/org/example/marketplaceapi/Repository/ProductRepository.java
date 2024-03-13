package org.example.marketplaceapi.Repository;

import org.example.marketplaceapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {
}

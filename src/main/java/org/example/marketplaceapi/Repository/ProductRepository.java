package org.example.marketplaceapi.Repository;

import org.example.marketplaceapi.model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findProductByName(String name);
}

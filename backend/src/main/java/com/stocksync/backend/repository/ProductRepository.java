package com.stocksync.backend.repository;

import com.stocksync.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // product is the data to be accessed and long is  the datatype for primary key
    // spring writes all crud code ..after extending it from jpa repo...
}

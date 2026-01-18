package com.stocksync.backend.service;

import com.stocksync.backend.model.Product;
import com.stocksync.backend.model.ProductRequest; // <--- Easy import now
import com.stocksync.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public Product addProduct(ProductRequest request){
        if(request.getPrice().doubleValue() < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .version(0L)
                .build();

        return productRepository.save(product);
    }
}
package com.stocksync.backend.controller;

import com.stocksync.backend.model.Product;
import com.stocksync.backend.model.ProductRequest; // <--- Easy import now
import com.stocksync.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return  ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.addProduct(request));
    }
}
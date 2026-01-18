package com.stocksync.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    // product name
    private String name;
    // description
    private String description;
    //price
    private BigDecimal price ;
     //items left
     private Integer stock;
     @Version // to prevent overselling at the same time
    private Long version;
}
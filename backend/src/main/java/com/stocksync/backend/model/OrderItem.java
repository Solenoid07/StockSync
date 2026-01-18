package com.stocksync.backend.model;

import jakarta.persistence.*;
import lombok.*;
import  java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //link back to the order (parent)
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private  Order order;

    //link to the prod (what was bought )
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    /* we store price here tooo
    ....so that when prod price changes
    ....the order history should show the old price...*/

    private BigDecimal priceAtPurchase;
}

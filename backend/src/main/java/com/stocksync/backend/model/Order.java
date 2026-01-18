package com.stocksync.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relation 1 ..many orders by 1 user ..
    @ManyToOne
    //creates a column named user_id that cant be null...
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //relation 2 ..one order .. can have many items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private BigDecimal totalAmount;






}

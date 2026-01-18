package com.stocksync.backend.repository;

import com.stocksync.backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // Usually empty. We rarely fetch items without fetching the Order first.
}
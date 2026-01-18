package com.stocksync.backend.repository;

import com.stocksync.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    /*find alll orders palced by a specific usser..
    in sql select * from orders where user_id = ?   */

    List<Order> findByUserId(Long userId);


}

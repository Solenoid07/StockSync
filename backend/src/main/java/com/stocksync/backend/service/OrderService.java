package com.stocksync.backend.service;

import com.stocksync.backend.model.*;
import com.stocksync.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List; // <--- ADDED THIS IMPORT FOR THE LIST

@Service //tells pring that this is a logic layer...
@RequiredArgsConstructor//lombok uses this for const. injection...

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //func ..if user buys a prod.
    @Transactional
    public Order placeOrder(Long userId, Long productId, int quantity){
        //1. find user.
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found."));

        //2. find the prod .
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found "));

        //3. check stock
        if (product.getStock() < quantity ) {
            throw new RuntimeException("Out of Stock!  Available: " + product.getStock());
        }

        //4. reduce stock (main update )
        // @version is used here...
        // if 2 people try it ..1 will fail...
        // @version is written in product.java..
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);//triggers version check as we have to save it ..so it automatically checks here...

        //5. creating the order ..
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.COMPLETED)
                .orderDate(LocalDateTime.now())
                .totalAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .build();


        // 6. create order time
        OrderItem item  = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .priceAtPurchase( product.getPrice())//freezes price ..even if we chck after price changed ..here it will be same as order history...
                .build();

        //7. link all
        order.setItems(java.util.List.of(item));
        //8. save all
        return orderRepository.save(order);

    }

    // NEW FUNC: Get all orders for a specific user
    // This helps us see the "History"
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

}
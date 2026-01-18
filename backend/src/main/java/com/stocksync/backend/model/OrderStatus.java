package com.stocksync.backend.model;

public enum OrderStatus {
    PENDING,//order placed, payment pending
    COMPLETED, // payment done, inventory deducted
    CANCELED //user canceled
}

package com.example.springbatchv1.model;

public record Stock(
        String symbol,
        String description,
        String category2,
        String category3,
        String GICSSector,
        String marketCap,
        String dividendYield,
        String priceToTTMEarnings,
        String priceToTTMSales,
        String priceToBookValue) {
}

package com.example.springbatchv1.persistence.enitities;

import javax.persistence.*;

@Entity
public class StockEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String description;

    private String category2;

    private String category3;

    private String GICSSector;

    private String marketCap;

    private String dividendYield;

    private String priceToTTMEarnings;

    private String priceToTTMSales;

    private String priceToBookValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getGICSSector() {
        return GICSSector;
    }

    public void setGICSSector(String GICSSector) {
        this.GICSSector = GICSSector;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(String dividendYield) {
        this.dividendYield = dividendYield;
    }

    public String getPriceToTTMEarnings() {
        return priceToTTMEarnings;
    }

    public void setPriceToTTMEarnings(String priceToTTMEarnings) {
        this.priceToTTMEarnings = priceToTTMEarnings;
    }

    public String getPriceToTTMSales() {
        return priceToTTMSales;
    }

    public void setPriceToTTMSales(String priceToTTMSales) {
        this.priceToTTMSales = priceToTTMSales;
    }

    public String getPriceToBookValue() {
        return priceToBookValue;
    }

    public void setPriceToBookValue(String priceToBookValue) {
        this.priceToBookValue = priceToBookValue;
    }
}

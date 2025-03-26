package com.example;

import lombok.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
public class FsApplication {
    String stockName;
    double stockPrice;
    List<Double> priceHistory = new ArrayList<>();

    public FsApplication(String stockName, double stockPrice) {
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        priceHistory.add(stockPrice);
    }

    public void updatePrice(double newPrice) {
        this.stockPrice = newPrice;
        priceHistory.add(newPrice);
    }

    public double calculateSMA(int period) {
        if (priceHistory.size() < period) return -1; // not enough data
        double sum = 0;
        for (int i = priceHistory.size() - period; i < priceHistory.size(); i++) {
            sum += priceHistory.get(i);
        }
        return sum / period;
    }

    public double calculateEMA(int period) {
        if (priceHistory.size() < period) return -1;
        double k = 2.0 / (period + 1);
        double ema = priceHistory.get(priceHistory.size() - period);
        for (int i = priceHistory.size() - period + 1; i < priceHistory.size(); i++) {
            ema = priceHistory.get(i) * k + ema * (1 - k);
        }
        return ema;
    }

}
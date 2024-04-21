package controller;

import java.util.Collections;
import java.util.TreeMap;

public class GaudiyOrder {

    private TreeMap<Double, Double> asks = new TreeMap<>();
    private TreeMap<Double, Double> bids = new TreeMap<>(Collections.reverseOrder());

    private double prevVol = 0;

    public double computeVolume(){

        double vol = bids.entrySet().stream().mapToDouble(i -> i.getKey() * i.getValue()).sum() +
                asks.entrySet().stream().mapToDouble(i -> i.getKey() * i.getValue()).sum();

        double updatedVol = vol - prevVol;
        prevVol = vol;
        return updatedVol;

    }

   public void updateOrderBooks(TreeMap<Double, Double> newAsks, TreeMap<Double, Double> newBids){
        this.asks = newAsks;
        this.bids = newBids;
    }

    public synchronized void showUpdatedOrders() {
        System.out.println("Bids:");
        bids.forEach((price, qty) -> System.out.println("Price: " + price + ", Quantity: " + qty));
        System.out.println("Asks:");
        asks.forEach((price, qty) -> System.out.println("Price: " + price + ", Quantity: " + qty));
    }


}
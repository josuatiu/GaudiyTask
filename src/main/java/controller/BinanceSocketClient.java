package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.GaudiyOrderBook;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class BinanceSocketClient extends WebSocketClient {

    private GaudiyOrder gaudiyOrder;

    public BinanceSocketClient(URI serverUri, GaudiyOrder gaudiyOrder) {
        super(serverUri);
        this.gaudiyOrder = gaudiyOrder;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Opening Connection...");
    }

    @Override
    public void onMessage(String message) {
        Gson gson = new Gson();
        GaudiyOrderBook orderBook = gson.fromJson(message, GaudiyOrderBook.class);

        TreeMap<Double, Double> newAsks = new TreeMap<>();
        TreeMap<Double, Double> newBids = new TreeMap<>(Collections.reverseOrder());

        for (List<String> ask : orderBook.getAsks()) {
            newAsks.put(Double.parseDouble(ask.get(0)), Double.parseDouble(ask.get(1)));
        }
        for (List<String> bid : orderBook.getBids()) {
            newBids.put(Double.parseDouble(bid.get(0)), Double.parseDouble(bid.get(1)));
        }

        gaudiyOrder.updateOrderBooks(newAsks, newBids);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("Closing Connection...");

    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}

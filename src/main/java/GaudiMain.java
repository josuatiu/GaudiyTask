import controller.BinanceSocketClient;
import controller.GaudiyOrder;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GaudiMain {
    public static void main (String [] args){

        GaudiyOrder ethOrders = new GaudiyOrder();
        GaudiyOrder btcOrders = new GaudiyOrder();

        BinanceSocketClient ethConnect = new BinanceSocketClient(URI.create("wss://stream.binance.com:9443/ws/ethusdt@depth20@100ms"), ethOrders);
        BinanceSocketClient btcConnect = new BinanceSocketClient(URI.create("wss://stream.binance.com:9443/ws/btcusdt@depth20@100ms"), btcOrders);

        ethConnect.connect();
        btcConnect.connect();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            double ethVolumeChange = ethOrders.computeVolume();
            double btcVolumeChange = btcOrders.computeVolume();
            System.out.println("ETH Volume Change: " + ethVolumeChange);
            System.out.println("BTC Volume Change: " + btcVolumeChange);
        }, 0, 10, TimeUnit.SECONDS);



    }


}

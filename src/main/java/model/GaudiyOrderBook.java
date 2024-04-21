package model;
import java.util.List;

public class GaudiyOrderBook {

    private List<List<String>> asks;
    private List<List<String>> bids;
    private long lastUpdateId;

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public long getPrevUpdateId() {
        return lastUpdateId;
    }

    public void setPrevUpdateId(long prevUpdateId) {
        this.lastUpdateId = prevUpdateId;
    }
}

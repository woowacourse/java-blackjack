package model.betting;

public class Profit {
    private final long profit;

    public Profit(long profit) {
        this.profit = profit;
    }

    public Profit add(long amount) {
        return new Profit(profit + amount);
    }

    public long getValue() {
        return profit;
    }
}

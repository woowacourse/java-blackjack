package domain;

public class PlayerScore {

    private final String name;
    private final Profit profit;

    public PlayerScore(String name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public Double getProfit() {
        return profit.getProfit();
    }
}

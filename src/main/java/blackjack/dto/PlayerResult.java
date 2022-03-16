package blackjack.dto;

public class PlayerResult {

    private final String name;
    private final int profit;

    public PlayerResult(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}

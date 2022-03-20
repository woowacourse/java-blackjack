package blackjack.dto;

public class PlayerResult {

    private final String name;
    private final String profit;

    public PlayerResult(String name, String profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public String getProfit() {
        return profit;
    }
}

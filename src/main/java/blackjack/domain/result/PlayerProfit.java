package blackjack.domain.result;

public class PlayerProfit {

    private final String name;
    private final int profit;

    public PlayerProfit(String name, int profit) {
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

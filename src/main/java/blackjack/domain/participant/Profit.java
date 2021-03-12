package blackjack.domain.participant;

public class Profit {
    private final double profit;

    public Profit(Double profit) {
        this.profit = new Double(profit);
    }

    public Double getProfit() {
        return this.profit;
    }
}

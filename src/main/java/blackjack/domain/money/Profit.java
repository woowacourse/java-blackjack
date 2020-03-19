package blackjack.domain.money;

public class Profit {

    private double profit;

    public Profit(double profit) {
        this.profit = profit;
    }

    public int getProfit() {
        return (int) profit;
    }
}

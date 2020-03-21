package blackjack.domain.result;

public class Profit {

    private final int profit;

    private Profit(int profit) {
        this.profit = profit;
    }

    public static Profit from(int profit) {
        return new Profit(profit);
    }

    public static Profit from(double profit) {
        return new Profit((int) profit);
    }

    public Profit minus(Profit playerProfit) {
        return Profit.from(profit - playerProfit.profit);
    }

    public int getProfit() {
        return profit;
    }
}

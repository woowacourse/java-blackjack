package vo;

public class Profit {
    private final BettingMoney bettingMoney;
    private int profit;

    public Profit(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.profit = bettingMoney.value();
    }

    public void apply(double ratio) {
        profit *= ratio;
    }

    public int getProfit() {
        return profit;
    }
}

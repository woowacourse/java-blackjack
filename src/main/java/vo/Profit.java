package vo;

import domain.result.ResultProfitRatio;

public class Profit {
    private final BettingMoney bettingMoney;
    private int profit;

    public Profit(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.profit = bettingMoney.value();
    }

    public void apply(ResultProfitRatio ratio) {
        profit *= ratio.getRatio();
    }

    public int getProfit() {
        return profit;
    }
}

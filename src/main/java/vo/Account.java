package vo;

import domain.result.ResultProfitRatio;

public class Account {
    private final BettingMoney bettingMoney;
    private Profit profit;

    public Account(BettingMoney bettingMoney, Profit profit) {
        this.bettingMoney = bettingMoney;
        this.profit = profit;
    }

    public Account(BettingMoney bettingMoney) {
        this(bettingMoney, new Profit(bettingMoney.value()));
    }

    public void applyProfit(ResultProfitRatio ratio) {
        profit = new Profit((int) (profit.value() * ratio.getRatio()));
    }

    public int getProfit() {
        return profit.value();
    }
}

package vo;

import domain.result.ResultProfitRatio;

public class Account {
    private final BettingMoney bettingMoney;
    private Profit profit;

    public Account(final BettingMoney bettingMoney, final Profit profit) {
        this.bettingMoney = bettingMoney;
        this.profit = profit;
    }

    public Account(final BettingMoney bettingMoney) {
        this(bettingMoney, new Profit(bettingMoney.value()));
    }

    public void applyProfit(final ResultProfitRatio ratio) {
        profit = new Profit((int) (profit.value() * ratio.getRatio()));
    }

    public int getProfit() {
        return profit.value();
    }
}

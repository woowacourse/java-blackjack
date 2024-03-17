package domain.account;

import domain.result.ResultProfitRatio;
import vo.BettingMoney;
import vo.Profit;

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
        profit = new Profit(profit.value() * ratio.getRatio());
    }

    public double getProfit() {
        return profit.value();
    }
}

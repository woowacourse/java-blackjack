package domain;

import domain.constant.Result;

public class Profit {

    private final int amount;

    public Profit(int betAmount, Result result) {
        this.amount = result.calculateProfit(betAmount);
    }

    public int getAmount() {
        return amount;
    }
}

package domain;

import domain.constant.Result;

public class BettingMoney {
    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public double calculateProceeds(Result result) {
        return bettingMoney * result.getAllocation();
    }
}

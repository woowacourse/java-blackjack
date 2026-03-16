package domain;

import domain.constant.Result;

public class PlayerStatus {
    private final BettingMoney bettingMoney;
    private boolean naturalBlackJack;

    public PlayerStatus(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public void markNaturalBlackJack() {
        naturalBlackJack = true;
    }

    public boolean isNaturalBlackJack() {
        return naturalBlackJack;
    }

    public double calculateProceeds(Result result) {
        return bettingMoney.calculateProceeds(result);
    }
}
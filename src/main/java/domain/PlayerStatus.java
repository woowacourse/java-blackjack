package domain;

import domain.constant.Result;

public class PlayerStatus {
    private final int bettingMoney;
    private boolean naturalBlackJack;

    public PlayerStatus(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public void markNaturalBlackJack() {
        naturalBlackJack = true;
    }

    public boolean isNaturalBlackJack() {
        return naturalBlackJack;
    }

    public double calculateProceeds(Result result) {
        return bettingMoney * result.getAllocation();
    }
}

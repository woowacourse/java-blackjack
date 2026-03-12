package domain;

import domain.constant.Result;

public class PlayerStatus {
    private final int bettingMoney;
    private Result result;

    public PlayerStatus(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public double calculateProceeds() {
        return bettingMoney * result.getAllocation();
    }

    public void renewedWithBlackJack() {
        result = Result.BLACKJACK;
    }

    public Result getResult() {
        return result;
    }
}

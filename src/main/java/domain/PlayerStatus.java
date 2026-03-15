package domain;

import domain.constant.Result;
import domain.game.ProceedsCalculator;

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
        return ProceedsCalculator.calculate(bettingMoney, result);
    }
}
package domain;

import domain.result.PlayerResult;

public class BettingMoney {
    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        if (bettingMoney < 0) {
            throw new NegativeMoneyException(bettingMoney);
        }
        this.bettingMoney = bettingMoney;
    }

    public int calculateEarning(PlayerResult playerResult) {
        return playerResult.multiply(bettingMoney);
    }
}

package domain;

import domain.result.PlayerResult;

public class BattingMoney {
    private final int battingMoney;

    public BattingMoney(int battingMoney) {
        if (battingMoney < 0) {
            throw new NegativeMoneyException();
        }
        this.battingMoney = battingMoney;
    }

    public int calculateEarning(PlayerResult playerResult) {
        return playerResult.multiply(battingMoney);
    }
}

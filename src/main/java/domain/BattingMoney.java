package domain;

import domain.result.PlayerResult;

public class BattingMoney {
    private final int battingMoney;

    public BattingMoney(int battingMoney) {
        if (battingMoney < 0) {
            throw new NegativeMoneyException("Money는 음수일 수 없습니다.");
        }
        this.battingMoney = battingMoney;
    }

    public int calculateEarning(PlayerResult playerResult) {
        return playerResult.multiply(battingMoney);
    }

    public int getBattingMoney() {
        return battingMoney;
    }
}

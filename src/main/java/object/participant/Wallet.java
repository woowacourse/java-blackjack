package object.participant;

import object.game.GameResult;

public class Wallet {
    private int betMoney;
    private int earnedMoney;

    public Wallet() {
        this.betMoney = 0;
        this.earnedMoney = 0;
    }

    public void applyBetRateBy(GameResult gameResult) {
        double betRate = gameResult.getBetRate();
        earnMoney(betMoney * betRate);
    }

    public void earnMoney(Number amount) {
        this.earnedMoney += amount.intValue();
        validateWallet();
    }

    public void betMoney(int amount) {
        betMoney += amount;
        validateWallet();
    }

    public int getProfit() {
        return earnedMoney - betMoney;
    }

    public int getBetMoney() {
        return betMoney;
    }

    private void validateWallet() {
        if (betMoney > 100_000_000) {
            throw new IllegalArgumentException("재정 문제로 1억 초과의 베팅은 받지 않습니다. 1억 이하의 베팅만 해주세요.");
        }
        if (betMoney < 0) {
            throw new IllegalArgumentException("음수의 돈을 베팅할 수 없습니다. 양수만 입력해주세요.");
        }
    }
}

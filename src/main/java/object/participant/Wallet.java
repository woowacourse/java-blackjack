package object.participant;

import object.game.GameResult;

public class Wallet {
    private int betMoney;
    private int earnedMoney;

    public Wallet() {
        this.betMoney = 0;
        this.earnedMoney = 0;
    }

    public void winBetRate(GameResult gameResult) {
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
        if (betMoney < 0) {
            throw new IllegalArgumentException("음수의 돈을 베팅할 수 없습니다. 양수만 입력해주세요.");
        }
        if (earnedMoney < 0) {
            throw new IllegalArgumentException("음수의 돈을 베팅할 수 없습니다. 양수만 입력해주세요.");
        }
    }
}

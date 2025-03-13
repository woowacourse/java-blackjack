package object.participant;

import object.game.GameResult;

public class Wallet {
    private int betMoney;
    private int earnedMoney;

    private Wallet(int betMoney, int earnedMoney) {
        this.betMoney = betMoney;
        this.earnedMoney = earnedMoney;
    }

    public static Wallet generateEmptyWalletFrom(int betMoney) {
        return new Wallet(betMoney, 0);
    }

    public void winBetRate(GameResult gameResult) {
        double betRate = gameResult.getBetRate();
        addEarnedMoney(betMoney * betRate);
    }

    public void addEarnedMoney(Number amount) {
        this.earnedMoney += amount.intValue();
    }

    public void bet(int amount) {
        betMoney += amount;
    }

    public int getProfit() {
        return earnedMoney - betMoney;
    }

    public int getBetMoney() {
        return betMoney;
    }
}

package object.participant;

import object.game.GameResult;

public class Wallet {
    private final int betMoney;
    private final int earnedMoney;

    private Wallet(int betMoney, int earnedMoney) {
        this.betMoney = betMoney;
        this.earnedMoney = earnedMoney;
    }

    public static Wallet generateEmptyWalletFrom(int betMoney) {
        return new Wallet(betMoney, 0);
    }

    public Wallet applyBetRate(GameResult gameResult) {
        double betRate = gameResult.getBetRate();
        return new Wallet(betMoney, (int) (betMoney * betRate));
    }

    public int getProfit() {
        return earnedMoney - betMoney;
    }
}

package object.participant;

import object.game.BattleResult;

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

    public Wallet applyBetRate(BattleResult battleResult) {
        if (battleResult == BattleResult.DRAW) {
            return new Wallet(betMoney, earnedMoney);
        }
        if (battleResult == BattleResult.WIN) {
            return new Wallet(betMoney, betMoney * 2);
        }

        return new Wallet(betMoney, 0);
    }

    public int getEarnedMoney() {
        return earnedMoney;
    }

    // TODO: 베팅 적용 추가, BattleResult 타입 추가-
}

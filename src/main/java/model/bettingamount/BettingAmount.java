package model.bettingamount;

import model.gameresult.GameResult;

public class BettingAmount {
    private static final double BLACKJACK_BONUS_RATE = 1.5;

    private final int value;

    public BettingAmount(final int value) {
        this.value = value;
    }

    public int getProfitByGameResult(GameResult result) {
        if (result == GameResult.WIN) {
            return getWinValue();
        }
        if (result == GameResult.DRAW) {
            return getDrawValue();
        }
        if (result == GameResult.LOSE) {
            return getLoseValue();
        }
        if (result == GameResult.BLACKJACK_WIN) {
            return getBlackjackWinValue();
        }
        throw new IllegalStateException("잘못된 게임 결과입니다.");
    }

    private int getWinValue() {
        return value;
    }

    private int getDrawValue() {
        return 0;
    }

    private int getLoseValue() {
        return -value;
    }

    private int getBlackjackWinValue() {
        return (int) (value * BLACKJACK_BONUS_RATE);
    }
}

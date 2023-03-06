package blackjack.domain;

import java.util.List;

public enum WinningResult {

    WIN,
    LOSE,
    PUSH,
    ;

    public static final int WIN_MAX_NUMBER = 21;


    public int getWinCount(final List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
                .filter(result -> result.equals(WIN))
                .count();
    }

    public int getLoseCount(final List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
                .filter(result -> result.equals(LOSE))
                .count();
    }

    public int getPushCount(final List<WinningResult> dealerResult) {
        return (int) dealerResult.stream()
                .filter(result -> result.equals(PUSH))
                .count();
    }

    public static WinningResult calculateByBurst(int playerValue) {
        if (playerValue > WIN_MAX_NUMBER) {
            return WIN;
        }
        return LOSE;
    }

    public static WinningResult calculateByBlackjack(boolean isPlayerBlackjack, boolean isDealerBlackjack) {
        if (isPlayerBlackjack && isDealerBlackjack) {
            return PUSH;
        }
        if (isPlayerBlackjack) {
            return LOSE;
        }
        return WIN;
    }

    public static WinningResult calculateByNumber(int playerValue, int dealerValue) {
        if (playerValue > dealerValue) {
            return LOSE;
        }
        if (playerValue < dealerValue) {
            return WIN;
        }
        return PUSH;
    }
}

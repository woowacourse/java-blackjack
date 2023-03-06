package blackjack.domain;

import java.util.Map;

public enum WinningResult {

    WIN,
    LOSE,
    PUSH,
    ;

    public static final int WIN_MAX_NUMBER = 21;


    public int getDealerLoseCount(final Map<Player, WinningResult> playersResult) {
        return (int) playersResult.values().stream()
                .filter(result -> result.equals(WIN))
                .count();
    }

    public int getDealerWinCount(final Map<Player, WinningResult> playersResult) {
        return (int) playersResult.values().stream()
                .filter(result -> result.equals(LOSE))
                .count();
    }

    public int getDealerPushCount(final Map<Player, WinningResult> playersResult) {
        return (int) playersResult.values().stream()
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

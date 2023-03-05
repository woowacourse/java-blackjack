package blackjack.domain;

import java.util.List;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    PUSH("무"),
    ;

    public static final int WIN_MAX_NUMBER = 21;
    final String name;

    WinningResult(final String name) {
        this.name = name;
    }

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

    public static WinningResult calculateByBurst(int playerValue, int dealerValue) {
        if (playerValue > WIN_MAX_NUMBER && dealerValue > WIN_MAX_NUMBER) {
            return PUSH;
        }
        if (playerValue > WIN_MAX_NUMBER) {
            return LOSE;
        }
        return WIN;
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

    public String getName() {
        return name;
    }
}

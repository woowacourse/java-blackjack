package blackjack.domain;

public enum WinningResult {

    WIN,
    LOSE,
    PUSH,
    ;

    public static final int WIN_MAX_NUMBER = 21;

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

    public WinningResult getPlayerResultByDealerResult() {
        if (this.isLose()) {
            return WIN;
        }
        return PUSH;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isPush() {
        return this == PUSH;
    }
}

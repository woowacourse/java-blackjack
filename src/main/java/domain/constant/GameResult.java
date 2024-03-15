package domain.constant;

public enum GameResult {
    BLACKJACK_WIN, WIN, LOSE, DRAW;

    public static GameResult getWinResult(final boolean isBlackJack) {
        if (isBlackJack) {
            return BLACKJACK_WIN;
        }

        return WIN;
    }

    public GameResult getReverseResult() {
        if (this == WIN || this == BLACKJACK_WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }
}

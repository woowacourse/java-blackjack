package blackjack.domain.result;

public enum GameResult {
    WIN("승"), LOSE("패"), DRAW("무");

    private static final int SCORE_LIMIT = 21;

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public static GameResult valueOf(int playerScore, int dealerScore) {
        if (isDealerWin(playerScore, dealerScore)) {
            return GameResult.LOSE;
        }

        if (isPlayerWin(playerScore, dealerScore)) {
            return GameResult.WIN;
        }

        return GameResult.DRAW;
    }

    private static boolean isDealerWin(int playerScore, int dealerScore) {
        if (playerScore > SCORE_LIMIT) {
            return true;
        }

        if (dealerScore > SCORE_LIMIT || dealerScore <= playerScore) {
            return false;
        }

        return true;
    }

    private static boolean isPlayerWin(int playerScore, int dealerScore) {
        if (playerScore > SCORE_LIMIT) {
            return false;
        }

        if (dealerScore > SCORE_LIMIT || dealerScore < playerScore) {
            return true;
        }

        return false;
    }

    public GameResult reverse() {
        if (this == GameResult.LOSE) {
            return GameResult.WIN;
        }
        if (this == GameResult.WIN) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}

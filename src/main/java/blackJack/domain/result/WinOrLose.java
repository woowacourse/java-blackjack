package blackJack.domain.result;

public enum WinOrLose {
    WIN("승"), DRAW("무"), LOSE("패");

    private static final int BLACK_JACK = 21;
    private final String result;

    WinOrLose(String result) {
        this.result = result;
    }

    public static WinOrLose calculateWinOrLose(int playerScore, int dealerScore) {
        if (overScore(playerScore)) {
            return LOSE;
        }
        if (overScore(dealerScore)) {
            return WIN;
        }
        return getWinOrLose(playerScore, dealerScore);
    }

    private static WinOrLose getWinOrLose(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static boolean overScore(int score) {
        return score > BLACK_JACK;
    }

    public String getResult() {
        return result;
    }
}

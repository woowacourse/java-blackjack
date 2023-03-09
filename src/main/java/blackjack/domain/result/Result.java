package blackjack.domain.result;

public enum Result {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private static final int SCORE_LIMIT = 21;
    private static final int INITIALIZED_SCORE = 0;

    private final String playerResult;

    Result(String playerResult) {
        this.playerResult = playerResult;
    }

    public static Result calculateResult(int playerScore, int dealerScore) {
        playerScore = initializeScoreIfBust(playerScore);
        dealerScore = initializeScoreIfBust(dealerScore);
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        return TIE;
    }

    private static int initializeScoreIfBust(int score) {
        if (score > SCORE_LIMIT) {
            return INITIALIZED_SCORE;
        }
        return score;
    }

    public Result ofOppositeResult() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return TIE;
    }

    public String getResult() {
        return this.playerResult;
    }
}

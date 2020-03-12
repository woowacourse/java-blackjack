package blackjack.domain.result;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BUST = 21;
    private final String word;

    Result(String word) {
        this.word = word;
    }

    public static Result findResultByScore(int playerScore, int dealerScore) {
        if (playerScore > BUST) {
            return LOSE;
        }
        if (dealerScore > BUST) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public static Result reverse(Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        return result;
    }

    public String getWord() {
        return word;
    }
}

package blackjack.domain.result;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BUST = 21;
    private final String word;

    ResultType(String word) {
        this.word = word;
    }

    public static ResultType findResultByScore(int playerScore, int dealerScore) {
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

    public static ResultType reverse(ResultType resultType) {
        if (resultType == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (resultType == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return resultType;
    }

    public String getWord() {
        return word;
    }
}

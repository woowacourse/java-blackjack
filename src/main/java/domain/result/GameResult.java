package domain.result;

public enum GameResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    ;

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public String displayName() {
        return result;
    }

    public static GameResult judge(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return LOSS;
        }

        if (dealerScore == playerScore) {
            return DRAW;
        }

        return WIN;
    }

    public GameResult reverseResult() {
        if (this == WIN) {
            return LOSS;
        }

        if (this == LOSS) {
            return WIN;
        }

        return this;
    }

}

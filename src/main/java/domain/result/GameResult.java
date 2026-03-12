package domain.result;

public enum GameResult {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    BLCAKJACK("블랙잭")
    ;

    private final String result;

    GameResult(String result) {
        this.result = result;
    }

    public String displayName() {
        return result;
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

package domain;

public enum
GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    public static final int BLACKJACK_SCORE = 21;

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static GameResult judge(int standardScore, int myScore) {
        if ((standardScore < myScore && myScore <= BLACKJACK_SCORE)
                || (standardScore > BLACKJACK_SCORE && myScore <= BLACKJACK_SCORE)) {
            return WIN;
        }
        if (standardScore > BLACKJACK_SCORE
                || (myScore <= BLACKJACK_SCORE && standardScore == myScore)) {
            return DRAW;
        }
        return LOSE;
    }
}

package domain;

import static domain.GameScore.BLACKJACK_SCORE;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String description;

    GameResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static GameResult judge(GameScore standardScore, GameScore myScore) {
        if ((myScore.isBiggerThan(standardScore) && myScore.isSameOrSmallerThan(BLACKJACK_SCORE))
                || (standardScore.isBiggerThan(BLACKJACK_SCORE) && myScore.isSameOrSmallerThan(BLACKJACK_SCORE))) {
            return WIN;
        }
        if (standardScore.isBiggerThan(BLACKJACK_SCORE)
                || (myScore.isSameOrSmallerThan(BLACKJACK_SCORE)) && standardScore.isSame(myScore)) {
            return DRAW;
        }
        return LOSE;
    }
}

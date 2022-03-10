package blackjack.domain;

public enum Score {
    WIN,
    DRAW,
    LOSE;

    public static Score inverse(Score score) {
        if (score == WIN) {
            return LOSE;
        }
        if (score == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}

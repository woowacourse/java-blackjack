package domain.card;

import domain.Score;

public enum Rank {
    ACE(Score.ONE),
    TWO(Score.TWO),
    THREE(Score.THREE),
    FOUR(Score.FOUR),
    FIVE(Score.FIVE),
    SIX(Score.SIX),
    SEVEN(Score.SEVEN),
    EIGHT(Score.EIGHT),
    NINE(Score.NINE),
    TEN(Score.TEN),
    J(Score.TEN),
    Q(Score.TEN),
    K(Score.TEN);

    private final Score score;

    Rank(Score score) {
        this.score = score;
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }

    public Score getScore() {
        return score;
    }
}

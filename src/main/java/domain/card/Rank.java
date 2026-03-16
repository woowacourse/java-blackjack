package domain.card;

import domain.Score;

public enum Rank {
    ACE("A", Score.ONE),
    TWO("2", Score.TWO),
    THREE("3", Score.THREE),
    FOUR("4", Score.FOUR),
    FIVE("5", Score.FIVE),
    SIX("6", Score.SIX),
    SEVEN("7", Score.SEVEN),
    EIGHT("8", Score.EIGHT),
    NINE("9", Score.NINE),
    TEN("10", Score.TEN),
    J("J", Score.TEN),
    Q("Q", Score.TEN),
    K("K", Score.TEN);

    private final String displayValue;
    private final Score score;

    Rank(String displayValue, Score scoreValue) {
        this.displayValue = displayValue;
        this.score = scoreValue;
    }

    public boolean isAce() {
        return this == Rank.ACE;
    }

    public Score getScore() {
        return score;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

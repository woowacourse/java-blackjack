package blackjack.domain.card;

import blackjack.domain.Score;

public enum Denomination {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final Score score;

    Denomination(final int score) {
        this.score = Score.valueOf(score);
    }

    public boolean isAce() {
        return this == ACE;
    }

    public Score getScore() {
        return score;
    }
}

package blackjack.domain.card;

import blackjack.domain.rule.Score;

public enum CardNumber {
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
    KING(10),
    ACE(11);

    static final Score ACE_BONUS_NUMBER = Score.from(1);
    private final Score score;

    CardNumber(int score) {
        this.score = Score.from(score);
    }

    public Score getScore() {
        return score;
    }
}

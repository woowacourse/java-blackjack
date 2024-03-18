package domain.playingcard;

import domain.participant.Score;

public enum PlayingCardValue {
    ACE(Score.valueOf(1)), TWO(Score.valueOf(2)), THREE(Score.valueOf(3)), FOUR(Score.valueOf(4)),
    FIVE(Score.valueOf(5)), SIX(Score.valueOf(6)), SEVEN(Score.valueOf(7)), EIGHT(Score.valueOf(8)),
    NINE(Score.valueOf(9)),
    TEN(Score.valueOf(10)), JACK(Score.valueOf(10)), QUEEN(Score.valueOf(10)), KING(Score.valueOf(10));

    private final Score score;

    PlayingCardValue(final Score score) {
        this.score = score;
    }

    public int getValue() {
        return score.value();
    }

    public boolean isAce() {
        return this == ACE;
    }
}

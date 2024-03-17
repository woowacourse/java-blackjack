package domain.playingcard;

import domain.participant.Score;
import domain.participant.Scores;

public enum PlayingCardValue {
    ACE(Scores.getScore(1)), TWO(Scores.getScore(2)), THREE(Scores.getScore(3)), FOUR(Scores.getScore(4)),
    FIVE(Scores.getScore(5)), SIX(Scores.getScore(6)), SEVEN(Scores.getScore(7)), EIGHT(Scores.getScore(8)),
    NINE(Scores.getScore(9)),
    TEN(Scores.getScore(10)), JACK(Scores.getScore(10)), QUEEN(Scores.getScore(10)), KING(Scores.getScore(10));

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

    public boolean isTenValueCard() {
        return score.value() == 10;
    }
}

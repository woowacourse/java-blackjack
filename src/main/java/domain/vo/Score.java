package domain.vo;

import domain.model.Card;
import domain.model.Cards;
import domain.type.Letter;
import java.util.Objects;

public class Score {

    private static final int MAX_VALUE = 21;
    private static final int ACE_SUB_NUMBER = 1;
    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score of(final Cards cards) {
        final int score = getInitialScore(cards);
        final int aceCount = cards.count(Letter.ACE);
        return new Score(modifyScoreByAce(score, aceCount));
    }

    public static Score of(final int value) {
        return new Score(value);
    }

    private static int getInitialScore(final Cards cards) {
        return cards.getCards()
            .stream()
            .mapToInt(Card::getNumber)
            .sum();
    }

    private static int modifyScoreByAce(int score, final int aceCount) {
        for (int i = 0; i < aceCount; i++) {
            score = changeToAceSub(score);
        }
        return score;
    }

    private static int changeToAceSub(int score) {
        if (score > MAX_VALUE) {
            score -= Letter.ACE.getNumber();
            score += ACE_SUB_NUMBER;
        }
        return score;
    }

    public boolean isBust() {
        return value > MAX_VALUE;
    }

    public boolean isStand() {
        return value <= MAX_VALUE;
    }

    public boolean isMax() {
        return value == MAX_VALUE;
    }

    public boolean lessThanOrEqualTo(final Score comparedScore) {
        return this.value <= comparedScore.value;
    }

    public boolean moreThan(final Score comparedScore) {
        return this.value > comparedScore.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score = (Score) o;
        return getValue() == score.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    public int getValue() {
        return value;
    }
}

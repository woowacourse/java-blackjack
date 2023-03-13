package blackjack.domain;

import java.util.Objects;

public class Score {

    public static final int MAX_VALUE = 21;
    private static final int DEALER_HIT_VALUE = 16;
    private static final int MAX_ACE_SCORE = 11;
    private static final int MIN_ACE_SCORE = 1;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score addAceScore(int aceCount) {
        int score = value;
        for (int restCount = aceCount; restCount > 0; restCount--) {
            int aceScore = decideAceScore(score, restCount);
            score += aceScore;
        }
        return new Score(score);
    }

    private int decideAceScore(int score, int restAceCount) {
        if (canAceHasMaxValue(score, restAceCount)) {
            return MAX_ACE_SCORE;
        }
        return MIN_ACE_SCORE;
    }

    private boolean canAceHasMaxValue(int score, int restAceCount) {
        final int ACE_VALUE_GAP = MAX_ACE_SCORE - MIN_ACE_SCORE;
        return (MAX_VALUE - score) - restAceCount * MIN_ACE_SCORE
            >= ACE_VALUE_GAP;
    }

    public boolean isBlackJackScore() {
        return value == MAX_VALUE;
    }

    public boolean isBust() {
        return value > MAX_VALUE;
    }

    public boolean isMoreThan(Score score) {
        return value > score.value;
    }

    public boolean canDealerHit() {
        return value <= DEALER_HIT_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}

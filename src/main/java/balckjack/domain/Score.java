package balckjack.domain;

import java.util.Objects;

public class Score {

    public static final Score BLACKJACK_SCORE = new Score(21);

    private static final int DEALER_HIT_NUMBER = 16;
    private static final Score maxAceValue = new Score(11);
    private static final Score minAceValue = new Score(1);


    private final int value;

    public Score() {
        this.value = 0;
    }

    public Score(final int value) {
        this.value = value;
    }

    public Score addAceScore(int aceCount) {
        Score score = new Score(value);
        for (int restCount = aceCount; restCount > 0; restCount--) {
            Score aceScore = decideAceScore(score, restCount);
            score = score.add(aceScore);
        }
        return score;
    }

    private Score decideAceScore(Score score, int restAceCount) {
        if (canAceHasMaxValue(score, restAceCount)) {
            return maxAceValue;
        }
        return minAceValue;
    }

    private boolean canAceHasMaxValue(Score score, int restAceCount) {
        final int ACE_VALUE_GAP = maxAceValue.value - minAceValue.value;
        return (BLACKJACK_SCORE.value - score.value) - restAceCount * minAceValue.value
            >= ACE_VALUE_GAP;
    }

    private Score add(Score score) {
        return new Score(value + score.value);
    }

    public boolean isBurst() {
        return value > BLACKJACK_SCORE.value;
    }

    public boolean isMoreThan(Score score) {
        return value > score.value;
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

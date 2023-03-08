package blackjack.domain.card;

import java.util.Objects;

public class Score {

    private static final int MAKE_ACE_BIGGER_SCORE = 10;
    private static final int BLACKJACK_SCORE_CONDITION = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score plusIfSoftHand() {
        if (value + MAKE_ACE_BIGGER_SCORE <= BLACKJACK_SCORE_CONDITION) {
            return new Score(value + MAKE_ACE_BIGGER_SCORE);
        }

        return this;
    }

    public boolean isBust() {
        return value > BLACKJACK_SCORE_CONDITION;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}

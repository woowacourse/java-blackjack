package blackjack.domain.card;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_PLUS = 10;
    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public Score addAceNumber() {
        int sumOfAce = value + ACE_PLUS;
        if (sumOfAce <= BLACKJACK_SCORE) {
            return new Score(sumOfAce);
        }
        return new Score(value);
    }

    public boolean isBlackJack() {
        return value == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isLessThan(Score score) {
        return value <= score.value;
    }

    public boolean isMoreCloseToBlackjack(Score score) {
        return Math.abs(BLACKJACK_SCORE - value) <= Math.abs(BLACKJACK_SCORE - score.value);
    }

    public int get() {
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
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package blackjack.domain.card;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_PLUS = 10;
    private static final Score BLACKJACK = new Score(BLACKJACK_SCORE);
    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public static Score ofBlackJack() {
        return BLACKJACK;
    }

    public Score addAceNumber() {
        int sumOfAce = value + ACE_PLUS;
        if(sumOfAce <= BLACKJACK_SCORE) {
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

    public boolean isLessThan(int score) {
        return value <= score;
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

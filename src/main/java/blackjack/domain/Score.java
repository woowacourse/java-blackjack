package blackjack.domain;

import java.util.Objects;

public class Score {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int ACE_OFFSET = -10;
    private static final int NUMBER_OF_INITIAL_CARD_NUMBER = 2;

    private final boolean isBlackJack;
    private final int value;

    public Score(int value, int numberOfCards) {
        this.value = value;
        this.isBlackJack = isBlackJackCondition(numberOfCards);
    }

    private boolean isBlackJackCondition(int numberOfCards) {
        return isMaxNumber() && numberOfCards == NUMBER_OF_INITIAL_CARD_NUMBER;
    }

    public boolean isMaxNumber() {
        return value == BLACKJACK_NUMBER;
    }

    public int getValue() {
        return value;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }

    public boolean isBust() {
        return value > BLACKJACK_NUMBER;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score = (Score) o;
        return isBlackJack == score.isBlackJack && value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBlackJack, value);
    }
}

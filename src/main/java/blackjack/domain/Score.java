package blackjack.domain;

import java.util.Objects;

public class Score {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int ACE_OFFSET = -10;
    private static final int NUMBER_OF_INITIAL_CARD_NUMBER = 2;

    private final boolean isBlackJack;
    private final int value;

    public Score(int value, int numberOfCards) {
        this.isBlackJack = isBlackJackCondition(value, numberOfCards);
        this.value = value;
    }

    private boolean isBlackJackCondition(int value, int numberOfCards) {
        return value == BLACKJACK_NUMBER && numberOfCards == NUMBER_OF_INITIAL_CARD_NUMBER;
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

    public GameResult getResultByCompareWith(Score targetScore) {
        if (this.isBust() || targetScore.isBust()) {
            return compareByBustCondition(targetScore);
        }
        return compareByScore(targetScore);
    }

    private GameResult compareByBustCondition(final Score targetScore) {
        if (this.isBust() && !targetScore.isBust()) {
            return GameResult.LOSE;
        }
        if (!this.isBust() && targetScore.isBust()) {
            return GameResult.WIN;
        }
        return GameResult.TIE;
    }

    private GameResult compareByScore(final Score targetScore) {
        if (this.value < targetScore.value) {
            return GameResult.LOSE;
        }
        if (this.value > targetScore.value) {
            return GameResult.WIN;
        }
        if (value == BLACKJACK_NUMBER) {
            return compareByBlackJackCondition(targetScore);
        }
        return GameResult.TIE;
    }

    private GameResult compareByBlackJackCondition(final Score targetScore) {
        if (this.isBlackJack && !targetScore.isBlackJack) {
            return GameResult.WIN;
        }
        if (!this.isBlackJack && targetScore.isBlackJack) {
            return GameResult.LOSE;
        }
        return GameResult.TIE;
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

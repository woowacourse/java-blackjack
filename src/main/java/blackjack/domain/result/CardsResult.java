package blackjack.domain.result;

import java.util.Objects;

public class CardsResult {

    private static final boolean HAS_NOT_ACE = false;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK_SCORE = 21;
    private static final int MINIMUM_SCORE_AND_COUNT = 1;
    private static final int ACE_WEIGHT = 10;
    private static final String BLACK_JACK = "BLACKJACK";
    private static final String BUST = "BUST";
    private static final String EQUAL_OR_UNDER_MINIMUM_SCORE_OR_COUNT_EXCEPTION_MESSAGE =
        String.format("최소값은 %d이상이어야 합니다.", MINIMUM_SCORE_AND_COUNT);

    private final int score;
    private final boolean blackjack;

    public CardsResult(int score) {
        this(score, HAS_NOT_ACE, MINIMUM_SCORE_AND_COUNT);
    }

    public CardsResult(int score, boolean hasAce, int count) {
        if (hasAce && score + ACE_WEIGHT <= BLACK_JACK_SCORE) {
            score += ACE_WEIGHT;
        }
        invalidScoreAndCount(score, count);
        this.score = score;
        this.blackjack = isBlackJack(score, count);
    }

    private void invalidScoreAndCount(int score, int count) {
        if (score < MINIMUM_SCORE_AND_COUNT || count < MINIMUM_SCORE_AND_COUNT) {
            throw new IllegalArgumentException(
                EQUAL_OR_UNDER_MINIMUM_SCORE_OR_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private static boolean isBlackJack(int score, int count) {
        return count == BLACK_JACK_CARD_COUNT && score == BLACK_JACK_SCORE;
    }

    public boolean isBust() {
        return score > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return blackjack;
    }

    public String getResult() {
        if (isBust()) {
            return BUST;
        }
        if (isBlackJack()) {
            return BLACK_JACK;
        }
        return String.valueOf(score);
    }

    public boolean isMoreThanScore(CardsResult cardsResult) {
        return this.score > cardsResult.score;
    }

    public boolean isEqualOrUnderScore(int score) {
        return this.score <= score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardsResult that = (CardsResult) o;
        return score == that.score &&
            blackjack == that.blackjack;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, blackjack);
    }
}

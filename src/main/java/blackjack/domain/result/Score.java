package blackjack.domain.result;

import blackjack.domain.card.Symbol;
import java.util.Objects;

public class Score {

    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final int BLACK_JACK_SCORE = 21;
    private static final int MINIMUM_SCORE_AND_COUNT = 1;
    private static final boolean DEFAULT_HAS_ACE = false;
    private static final String EQUAL_OR_UNDER_MINIMUM_SCORE_OR_COUNT_EXCEPTION_MESSAGE =
        String.format("최소값은 %d이상이어야 합니다.", MINIMUM_SCORE_AND_COUNT);

    private final int score;
    private final int count;

    public Score(int score) {
        this(score, DEFAULT_HAS_ACE, MINIMUM_SCORE_AND_COUNT);
    }

    public Score(int score, boolean hasAce, int count) {
        if (hasAce && score + Symbol.ACE_WEIGHT <= BLACK_JACK_SCORE) {
            score += Symbol.ACE_WEIGHT;
        }
        invalidScoreAndCount(score, count);
        this.score = score;
        this.count = count;
    }

    private void invalidScoreAndCount(int score, int count) {
        if (score < MINIMUM_SCORE_AND_COUNT || count < MINIMUM_SCORE_AND_COUNT) {
            throw new IllegalArgumentException(
                EQUAL_OR_UNDER_MINIMUM_SCORE_OR_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public boolean isBust() {
        return score > BLACK_JACK_SCORE;
    }

    public boolean isBlackJack() {
        return count == BLACK_JACK_CARD_COUNT && score == BLACK_JACK_SCORE;
    }

    public int getScore() {
        return score;
    }

    public boolean isOverScore(Score score) {
        return this.score > score.score;

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
        return score == score1.score &&
            isBlackJack() == score1.isBlackJack();
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, isBlackJack());
    }
}

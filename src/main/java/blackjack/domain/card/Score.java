package blackjack.domain.card;

import java.util.Objects;
import java.util.stream.IntStream;

public class Score {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final int DEALER_RECEIVABLE_SCORE = 17;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BUST_THRESHOLD = 21;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score soft(IntStream scores) {
        int best = scores.sum();
        if (best + ACE_ADDITIONAL_NUMBER > BUST_THRESHOLD) {
            return new Score(best);
        }
        return new Score(best + ACE_ADDITIONAL_NUMBER);
    }

    public static Score hard(IntStream scores) {
        return new Score(scores.sum());
    }

    public boolean isBusted() {
        return score > BUST_THRESHOLD;
    }

    public boolean isBlackJack() {
        return score == BLACKJACK_SCORE;
    }

    public boolean isDealerReceivable() {
        return score < DEALER_RECEIVABLE_SCORE;
    }

    public boolean isBiggerThan(Score score) {
        return this.score > score.getScore();
    }

    public int getScore() {
        return score;
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
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}

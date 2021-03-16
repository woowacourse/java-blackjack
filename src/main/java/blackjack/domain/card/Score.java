package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {
    public static final int MAX_SCORE = 44;
    public static final int BLACKJACK = 21;
    public static final int TEN = 10;

    private static final List<Score> CACHE = new ArrayList<>();
    private final int score;

    static {
        for (int i = 0; i <= MAX_SCORE; i++) {
            CACHE.add(new Score(i));
        }
    }

    private Score(int score) {
        validate(score);
        this.score = score;
    }

    private void validate(int score) {
        if (score < 0 || score > MAX_SCORE) {
            throw new IllegalArgumentException("처리될 수 없는 점수값입니다!");
        }
    }

    public static Score of(int value) {
        Score score = CACHE.get(value);

        if (Objects.isNull(score)) {
            score = new Score(value);
        }
        return score;
    }

    public Score plusTenIfNotBust() {
        Score score = Score.of(CACHE.get(TEN).score + this.score);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    public int toInt() {
        return score;
    }

    public boolean isBust() {
        return CACHE.get(BLACKJACK).lessThan(Score.of(score));
    }

    protected boolean isBlackjack() {
        return CACHE.get(BLACKJACK).equals(Score.of(score));
    }

    public boolean lessThan(Score other) {
        return this.score < other.score;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Score) {
            return ((Score) o).score == this.score;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}

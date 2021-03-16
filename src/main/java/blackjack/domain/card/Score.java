package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Score {
    public static final int BLACKJACK = 21;
    public static final int TEN = 10;

    private final int score;
    private static final List<Score> CACHE = new ArrayList<>();

    static {
        for (int i = 0; i <= 44; i++) {
            CACHE.add(new Score(i));
        }
    }

    private Score(int score) {
        this.score = score;
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

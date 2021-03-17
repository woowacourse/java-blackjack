package blackjack.domain.card;

import java.util.*;

public class Score {
    public static final int MAX_SCORE = 44;

    private static final Map<Integer, Score> CACHE = new WeakHashMap<>(MAX_SCORE);
    public static final Score BLACKJACK = Score.of(21);
    public static final Score TEN = Score.of(10);

    private final int value;

    private Score(int score) {
        validate(score);
        this.value = score;
    }

    private void validate(int score) {
        if (score < 0 || score > MAX_SCORE) {
            throw new IllegalArgumentException("처리될 수 없는 점수값입니다!");
        }
    }

    public static Score of(int value) {
        return CACHE.computeIfAbsent(value, Score::new);
    }

    public Score plusTenIfNotBust() {
        Score score = add(TEN);
        if (score.isBust()) {
            return this;
        }
        return score;
    }

    private Score add(Score score) {
        return Score.of(this.value + score.value);
    }

    public int toInt() {
        return value;
    }

    public boolean isBust() {
        return BLACKJACK.lessThan(Score.of(value));
    }

    protected boolean isBlackjack() {
        return BLACKJACK.equals(Score.of(value));
    }

    public boolean lessThan(Score other) {
        return this.value < other.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

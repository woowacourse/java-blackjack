package blackjack.model.card;

import java.util.List;

public class Score {
    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score from(final List<Score> scores) {
        int sum = scores.stream()
                .mapToInt(Score::getValue)
                .sum();
        return new Score(sum);
    }

    public Score plus(final int score) {
        return new Score(score + value);
    }

    public boolean equalTo(final int criterion) {
        return value == criterion;
    }

    public boolean equalToOrLessThan(final int criterion) {
        return value <= criterion;
    }

    public boolean lessThan(final int criterion) {
        return value < criterion;
    }

    public boolean equalToOrGreaterThan(final Score criterion) {
        return value >= criterion.value;
    }

    public boolean greaterThan(final int criterion) {
        return value > criterion;
    }

    public int getValue() {
        return value;
    }
}

package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Score {
    private final int value;

    public static Score of(int... values) {
        int sum = Arrays.stream(values).sum();
        return new Score(sum);
    }

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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

    public Score plus(int increment) {
        return new Score(value + increment);
    }
}

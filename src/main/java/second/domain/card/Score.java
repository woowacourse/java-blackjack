package second.domain.card;

import second.domain.IScore;

import java.util.Objects;

public class Score implements IScore {
    private final int value;

    Score(final int value) {
        this.value = value;
    }

    @Override
    public Score plus(int value) {
        return new Score(this.value + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

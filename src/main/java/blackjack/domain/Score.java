package blackjack.domain;

public class Score implements Comparable<Score> {

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isLessThan(int value) {
        return this.value < value;
    }

    @Override
    public int compareTo(Score target) {
        return Integer.compare(value, target.value);
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
        return value;
    }
}

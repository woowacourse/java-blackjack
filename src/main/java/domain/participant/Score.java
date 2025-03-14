package domain.participant;

public record Score(int value) {
    private static final Score ZERO = new Score(0);

    public static Score zero() {
        return ZERO;
    }

    public static Score from(int value) {
        return new Score(value);
    }

    public int toInt() {
        return value;
    }

    public Score minus(Score minus) {
        return new Score(value - minus.value);
    }

    public boolean isLessThanEqual(Score other) {
        return this.value() <= other.value();
    }

    public boolean isLessThan(Score other) {
        return this.value() < other.value();
    }

    public boolean isEqualTo(Score other) {
        return this.value() == other.value();
    }

    public boolean isGreaterThan(Score other) {
        return this.value > other.value();
    }
}

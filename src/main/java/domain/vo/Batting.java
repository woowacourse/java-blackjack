package domain.vo;

public class Batting {

    private final double value;

    private Batting(final double value) {
        this.value = value;
    }

    public static Batting of(final double value) {
        return new Batting(value);
    }

    public double getValue() {
        return value;
    }
}

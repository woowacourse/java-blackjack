package domain.vo;

public class Batting {

    private static final String NEGATIVE_VALUE_ERROR_MESSAGE = "배팅 금액은 음수가 될 수 없습니다.";
    private final double value;

    private Batting(final double value) {
        validate(value);
        this.value = value;
    }

    public static Batting of(final double value) {
        return new Batting(value);
    }

    private void validate(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_VALUE_ERROR_MESSAGE);
        }
    }

    public double getValue() {
        return value;
    }
}

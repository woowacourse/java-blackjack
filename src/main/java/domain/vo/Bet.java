package domain.vo;

public class Bet {

    private static final String NEGATIVE_VALUE_ERROR_MESSAGE = "배팅 금액은 음수가 될 수 없습니다.";
    private final double value;

    private Bet(final double value) {
        validate(value);
        this.value = value;
    }

    public static Bet of(final double value) {
        return new Bet(value);
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

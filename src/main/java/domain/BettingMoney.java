package domain;

public class BettingMoney {
    private static final String INVALID_NUMBER_MESSAGE = "1000 단위만 가능합니다.";
    private static final int MIN = 1000;

    private final int value;

    public BettingMoney(final int number) {
        checkNumber(number);
        this.value = number;
    }

    private void checkNumber(final int number) {
        if (number < MIN || number % MIN != 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}

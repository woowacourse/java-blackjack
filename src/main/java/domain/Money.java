package domain;

public class Money {

    private static final int NATURAL_NUMBER_BOUND = 1;
    private static final String MONEY_BOUND_ERROR_MESSAGE = "1 이상의 금액만 배팅이 가능합니다.";

    private final int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        if (value <= NATURAL_NUMBER_BOUND) {
            throw new IllegalArgumentException(MONEY_BOUND_ERROR_MESSAGE);
        }

        return new Money(value);
    }

    public int getValue() {
        return value;
    }

}

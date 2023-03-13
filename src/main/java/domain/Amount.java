package domain;

import java.util.Objects;

public class Amount {

    private static final int NATURAL_NUMBER_BOUND = 1;
    private static final String AMOUNT_BOUND_ERROR_MESSAGE = "1 이상의 금액만 배팅이 가능합니다.";

    private final int value;

    public Amount(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (isNotNaturalNumber(value)) {
            throw new IllegalArgumentException(AMOUNT_BOUND_ERROR_MESSAGE);
        }
    }

    private boolean isNotNaturalNumber(int value) {
        return value <= NATURAL_NUMBER_BOUND;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}

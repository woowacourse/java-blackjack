package domain;

import java.util.Objects;

public class Profit {

    private final long value;

    public Profit(final long value) {
        validateMultiplesOfTen(value);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    private static void validateMultiplesOfTen(final long value) {
        if (value % 10 != 0) {
            throw new IllegalStateException("[ERROR] 금액은 10의 배수만 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Profit profit)) {
            return false;
        }

        return Objects.equals(value, profit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

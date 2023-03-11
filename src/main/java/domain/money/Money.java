package domain.money;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private final BigDecimal value;

    protected Money(int value) {
        this.value = new BigDecimal(value);
    }

    public static Money valueOf(int value) {
        return new Money(value);
    }

    public int getValue() {
        return value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return value.intValue() == that.value.intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

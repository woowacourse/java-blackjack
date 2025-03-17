package domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Profit(
        BigDecimal value
) {
    public int toIntValue() {
        return value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profit profit)) {
            return false;
        }
        return Objects.equals(value, profit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

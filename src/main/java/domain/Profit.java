package domain;

import java.math.BigDecimal;

public record Profit(
        BigDecimal value
) {

    public int toIntValue() {
        return value.intValue();
    }
}

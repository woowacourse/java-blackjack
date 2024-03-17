package domain.money;

import java.math.BigDecimal;

public class Profit {
    private final BigDecimal value;

    public Profit(BigDecimal value) {
        this.value = value;
    }

    public Profit sum(Profit profit) {
        return new Profit(value.add(profit.getValue()));
    }

    public Profit reverse() {
        return new Profit(value.multiply(new BigDecimal("-1")));
    }

    public BigDecimal getValue() {
        return value;
    }
}

package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private static final BigDecimal MINIMUM_MONEY = new BigDecimal(5000);
    private static final BigDecimal MAXIMUM_MONEY = new BigDecimal(500000);
    private static final String INVALID_MONEY_RANGE = String.format("베팅 금액은 %s원 이상, %s원 이하만 가능합니다.", MINIMUM_MONEY, MAXIMUM_MONEY);
    private final BigDecimal value;

    public Money(final String value) {
        BigDecimal money = new BigDecimal(value);
        validateRange(money);
        this.value = money;
    }

    private void validateRange(final BigDecimal money) {
        if (lessThanMinimum(money) || moreThanMaximum(money)) {
            throw new IllegalArgumentException(INVALID_MONEY_RANGE);
        }
    }

    private boolean lessThanMinimum(final BigDecimal money) {
        return money.compareTo(MINIMUM_MONEY) < 0;
    }

    private boolean moreThanMaximum(final BigDecimal money) {
        return money.compareTo(MAXIMUM_MONEY) > 0;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return Objects.equals(value, money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

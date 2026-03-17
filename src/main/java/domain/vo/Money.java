package domain.vo;

import java.math.BigDecimal;

public class Money {
    private static final BigDecimal MIN_INCREMENT = BigDecimal.valueOf(10_000);
    private final BigDecimal money;

    public Money(String input) {
        BigDecimal value = validateAndParse(input);
        validateMinIncrement(value);
        this.money = value;
    }

    public Money(BigDecimal input) {
        this.money = input;
    }

    public Money add(Money other) {
        return new Money(this.money.add(other.money));
    }

    public BigDecimal multiplyDouble(double input) {
        return this.money.multiply(BigDecimal.valueOf(input));
    }

    public long toLong() {
        return money.longValue();
    }

    public Money negate() {
        return new Money(this.money.negate());
    }

    private BigDecimal validateAndParse(String input) {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] " + MIN_INCREMENT + "원 이상의 정수를 입력해야 하는데, 현재 입력값이 " + input + "입니다.");
        }
    }

    private void validateMinIncrement(BigDecimal value) {
        if (value.compareTo(MIN_INCREMENT) < 0) {
            throw new IllegalArgumentException("[ERROR] 최소 금액은 " + MIN_INCREMENT + "원입니다. 그런데 현재 입력값이 " + value.toPlainString() + "입니다.");
        }

        if (value.remainder(MIN_INCREMENT).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("[ERROR] " + MIN_INCREMENT + "원 단위여야 하며, 최소 금액은 " + MIN_INCREMENT + "합니다. "
                    + "그런데 현재 입력값이" + value.toPlainString() + "입니다.");
        }
    }
}

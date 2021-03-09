package blackjack.domain;

import blackjack.exception.InvalidMoneyInputException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Money {

    private static final Pattern VALID_MONEY_FORMAT = Pattern.compile("^[-]?[0-9]*$");
    private static final String DEFAULT_MONEY_VALUE = "0";
    private static final String TO_STRING_FORMAT = "%.0f";
    private final double value;

    public Money(String value) {
        validateMoneyInput(value);
        this.value = Integer.parseInt(value);
    }

    public Money() {
        this(DEFAULT_MONEY_VALUE);
    }

    private void validateMoneyInput(String value) {
        if (value == null || value.isEmpty() || !VALID_MONEY_FORMAT.matcher(value).matches()) {
            throw new InvalidMoneyInputException("숫자를 입력해야 합니다.");
        }
    }

    public Money update(Double updateAmount) {
        return new Money(format(value + updateAmount));
    }

    public double getValue() {
        return value;
    }

    private String format(Double value) {
        return String.format(TO_STRING_FORMAT, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(String amount) {
        validate(amount);
        this.amount = new BigDecimal(amount);
    }

    private void validate(String amount) {
        validateNull(amount);
        validateNumeric(amount);
    }

    private void validateNull(String amount) {
        if (amount == null) {
            throw new IllegalArgumentException("금액 문자열은 null이 될 수 없습니다.");
        }
    }

    private void validateNumeric(String amount) {
        try {
            Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액과 관련된 연산은 숫자만 입력 가능합니다.");
        }
    }

    public Money add(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money subtract(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money multiply(String ratio) {
        validate(ratio);
        return new Money(this.amount.multiply(new BigDecimal(ratio)));
    }

    public Money negative() {
        return new Money(this.amount.negate());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return this.amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}

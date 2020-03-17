package blackjack.domain.participants;

import java.util.Objects;

import blackjack.exceptions.InvalidMoneyException;

public class Money {
    private long amount;

    public Money(final long amount) {
        this.amount = amount;
    }

    public Money(final String value) {
        this(validate(value));
    }

    private static long validate(String value) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new InvalidMoneyException("Null 또는 빈 값을 입력하셨습니다.");
        }
        try {
            return validatePositive(Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException("잘못된 값을 입력하셨습니다.");
        }
    }

    private static long validatePositive(final long parsed) {
        if (parsed < 0) {
            throw new NumberFormatException();
        }
        return parsed;
    }

    public long getAmount() {
        return amount;
    }

    public Money add(final Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money multiply(final Money other) {
        return new Money(this.amount * other.amount);
    }

    public Money subtract(final Money other) {
        return new Money(this.amount - other.amount);

    }
}

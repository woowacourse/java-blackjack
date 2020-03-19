package blackjack.domain.participants.money;

import java.util.Objects;

import blackjack.exceptions.InvalidMoneyException;

public class Money {
    private double amount;

    private Money(final double amount) {
        this.amount = amount;
    }

    public static Money create(final String input) {
        return new Money(validAmount(input));
    }

    private static double validAmount(final String input) {
        if (Objects.isNull(input) || input.trim().isEmpty()) {
            throw new InvalidMoneyException("입력값은 빈 값일 수 없습니다.");
        }
        try {
            double parsed = Double.parseDouble(input);
            validatePositive(parsed);
            return parsed;
        } catch (NumberFormatException | InvalidMoneyException e) {
            throw new InvalidMoneyException("입력값은 0보다 큰 숫자여야 합니다.");
        }
    }

    private static void validatePositive(final double parsed) {
        if (parsed <= 0) {
            throw new InvalidMoneyException();
        }
    }

    public Money add(final Money other) {
        return new Money(amount + other.amount);
    }

    public double getAmount() {
        return amount;
    }

    public Money subtract(final Money other) {
        return new Money(amount - other.amount);
    }

    public Money multiply(final Money other) {
        return new Money(amount * other.amount);
    }
}

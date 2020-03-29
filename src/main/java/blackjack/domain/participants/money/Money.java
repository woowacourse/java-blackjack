package blackjack.domain.participants.money;

import java.util.Objects;

import blackjack.exceptions.InvalidMoneyException;

public class Money {
    private double amount;

    private Money(final double amount) {
        this.amount = amount;
    }

    public Money() {
        this(0);
    }

    public Money(final String input) {
        this(validAmount(input));
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

    public void add(final Money other) {
        amount += other.amount;
    }

    public void subtract(final Money other) {
        amount -= other.amount;
    }

    public void multiply(double times) {
        amount *= times;
    }

    public double getAmount() {
        return amount;
    }

    public void getOpposite() {
        amount = -amount;
    }

    @Override
    public String toString() {
        return String.format("%.0f", amount);
    }
}

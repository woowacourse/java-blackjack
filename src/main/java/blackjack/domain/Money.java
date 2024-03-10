package blackjack.domain;

import java.util.Objects;

class Money {

    private static final int MIN_MONEY = 1;

    private final int money;

    public Money(int money) {
        validateMoneyRange(money);

        this.money = money;
    }

    private void validateMoneyRange(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("돈은 0보다 커야 합니다.");
        }
    }

    public Money multiply(double multiplier) {
        return new Money((int) (money * multiplier));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

package blackjack.domain.bet;

import java.util.Objects;

public class Money {
    public static final String POSITIVE_MONEY_MESSAGE = "금액은 양수여야 합니다.";
    private final int money;

    public Money(int money) {
        validatePositive(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(POSITIVE_MONEY_MESSAGE);
        }
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

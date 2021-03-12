package blackjack.domain;

import java.util.Objects;

public class Money {
    private final long money;

    private Money(long money) {
        this.money = money;
    }

    public static Money of(long money) {
        validate(money);
        return new Money(money);
    }

    private static void validate(long money) {
        if (money < 0) {
            throw new IllegalArgumentException("음수는 받을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

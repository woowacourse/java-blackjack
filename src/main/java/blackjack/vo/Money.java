package blackjack.vo;

import java.util.Objects;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public Money() {
        this(0);
    }

    public static Money fromInput(int money) {
        validateInputValue(money);
        return new Money(money);
    }

    private static void validateInputValue(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("베팅 금액은 양수이어야한다.");
        }
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("베팅 금액은 1000단위의 숫자이어야한다.");
        }
    }

    public int value() {
        return money;
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

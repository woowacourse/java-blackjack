package blackjack.domain;

import java.util.Objects;

public class Money {

    public static final int MIN = 10000;
    public static final int MAX = 100000;

    private final int value;

    public Money(int value) {
        if (MIN > value || value > MAX) {
            throw new IllegalArgumentException("범위가 올바르지 않습니다.");
        }
        this.value = value;
    }

    public static final Money of(String value) {
        try {
            int parsed = Integer.parseInt(value);
            return new Money(parsed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자 형태가 아닙니다.");
        }
    }


    public int getValue() {
        return value;
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
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public Money product(double factor) {
        return new Money((int) (value * factor));
    }
}

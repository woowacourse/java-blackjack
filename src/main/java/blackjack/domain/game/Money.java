package blackjack.domain.game;

import java.util.Objects;

public class Money {

    private final int value;

    public Money(final String value) {
        this.value = validateInteger(value);
    }

    private int validateInteger(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 정수만 가능합니다.");
        }
    }

    public Money changeSign() {
        return new Money(String.valueOf(value * -1));
    }
    
    public Money makeZero() {
        return new Money(String.valueOf(0));
    }

    public Money multiplyOneAndHalf() {
        return new Money(String.valueOf((int) Math.floor(value * 1.5)));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}

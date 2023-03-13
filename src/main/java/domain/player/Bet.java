package domain.player;

import java.util.Objects;

public class Bet {
    private final int value;

    public Bet(int value) {
        this.value = value;
    }

    public static Bet from(int value) {
        validateValue(value);
        return new Bet(value);
    }

    public static Bet zero() {
        return new Bet(0);
    }

    public Bet toNegative() {
        return new Bet(value * -1);
    }

    public Bet multiply(float multiplyValue) {
        return new Bet((int) (this.value * multiplyValue));
    }

    private static void validateValue(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅액은 0보다 커야 합니다.");
        }
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("베팅액은 1,000원 단위로 입력해야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return value == bet.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "value=" + value +
                '}';
    }
}

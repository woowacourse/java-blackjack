package domain.stake;

import domain.player.Status;

import java.util.Objects;

public final class Bet {

    private static final int MIN_BET = 100;
    private static final int MAX_BET = 100_000;
    private static final int NEGATIVE_VALUE = -1;

    private final int value;

    private Bet(final int value) {
        this.value = value;
    }

    public static Bet from(final int value) {
        validate(value);
        return new Bet(value);
    }

    private static void validate(final int value) {
        if (value < MIN_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100 미만일 수 없습니다.");
        }
        if (value > MAX_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100,000 초과일 수 없습니다.");
        }
    }

    public Bet getPrize(final Status status) {
        return new Bet((int) (value * status.getMultiplyRatio()));
    }

    public Bet negate() {
        return new Bet(value * NEGATIVE_VALUE);
    }

    public Bet add(final Bet bet) {
        return new Bet(this.value + bet.value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return value == bet.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

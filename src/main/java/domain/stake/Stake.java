package domain.stake;

import domain.player.Status;

import java.util.Objects;

public final class Stake {

    private static final int MIN_BET = 100;
    private static final int MAX_BET = 100_000;
    private static final int PLAYER_NEGATE = -1;

    private final int value;

    private Stake(final int value) {
        this.value = value;
    }

    public static Stake fromBet(final int value) {
        validate(value);
        return new Stake(value);
    }

    private static void validate(final int value) {
        if (value < MIN_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100 미만일 수 없습니다.");
        }
        if (value > MAX_BET) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 100,000 초과일 수 없습니다.");
        }
    }

    public Stake getPrize(final Status status) {
        return new Stake((int) (value * status.getMultiplyRatio()));
    }

    public Stake negate() {
        return new Stake(value * -1);
    }

    public Stake add(final Stake stake) {
        return new Stake(this.value + stake.value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stake stake = (Stake) o;
        return value == stake.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

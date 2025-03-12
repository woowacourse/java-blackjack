package domain;

import java.util.Objects;

public class Bet {

    private static final int MIN_BET = 1_000;
    private static final int MAX_BET = 1_000_000;
    private static final Bet DEFAULT_BET = new Bet(MIN_BET);

    private final int value;

    public Bet(int value) {
        validateRange(value);
        this.value = value;
    }

    public static Bet defaultBet() {
        return DEFAULT_BET;
    }

    private void validateRange(int value) {
        if (value < MIN_BET || value > MAX_BET) {
            throw new IllegalArgumentException("베팅 금액은 " + MIN_BET + "이상 " + MAX_BET + "이하여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Bet bet)) {
            return false;
        }
        return value == bet.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}

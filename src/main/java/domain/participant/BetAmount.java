package domain.participant;

import java.util.Objects;

public final class BetAmount {

    public static final int MIN_BET_AMOUNT = 500;
    private final int value;

    private BetAmount(int value) {
        this.value = value;
    }

    public static BetAmount from(int value) {
        if (value <= MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야합니다.");
        }
        return new BetAmount(value);
    }

    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount1 = (BetAmount) o;
        return value == betAmount1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

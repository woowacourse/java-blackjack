package blackJack.domain.money;

import java.util.Objects;

public class Bet {

    public static final Bet ZERO = new Bet(0);
    private static final int MINIMUM_VALUE = 0;

    private final int value;

    public Bet(final int value) {
        validateBettingMoney(value);
        this.value = value;
    }

    private void validateBettingMoney(int value) {
        if (value < MINIMUM_VALUE) {
            throw new IllegalArgumentException("베팅금액은 음수일 수 없습니다.");
        }
    }

    public Bet add(final int money) {
        return new Bet(value + money);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bet)) {
            return false;
        }
        Bet that = (Bet) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

package blackjack.domain.participants;

import java.util.Objects;

public class GamblingMoney {
    private static final int INITIAL_MONEY = 0;

    private final int value;

    public GamblingMoney(int value) {
        this.value = value;
    }

    public GamblingMoney() {
        this.value = INITIAL_MONEY;
    }

    public GamblingMoney add(GamblingMoney otherGamblingMoney) {
        return new GamblingMoney(this.value + otherGamblingMoney.value);
    }

    public GamblingMoney subtract(GamblingMoney otherGamblingMoney) {
        return new GamblingMoney(this.value - otherGamblingMoney.value);
    }

    public GamblingMoney multiply(float number) {
        return new GamblingMoney(Math.round(this.value * number));
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
        GamblingMoney gamblingMoney = (GamblingMoney) o;
        return value == gamblingMoney.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

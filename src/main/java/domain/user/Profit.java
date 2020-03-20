package domain.user;

import java.util.Objects;

public class Profit {
    private int value;

    Profit(int value) {
        this.value = value;
    }

    public Profit(Money money) {
        value = (int) money.getValue();
    }

    Profit multiply(int value) {
        return new Profit(this.value * value);
    }

    Profit add(Profit other) {
        return new Profit(this.value + other.value);
    }

    int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

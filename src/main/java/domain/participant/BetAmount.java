package domain.participant;

import java.util.Objects;

public class BetAmount {

    private final int value;

    public BetAmount(int value) {
        this.value = value;
    }

    public BetAmount(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
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

    @Override
    public String toString() {
        return "BetAmount{" +
                "betAmount=" + value +
                '}';
    }
}

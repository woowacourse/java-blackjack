package domain;

import java.util.Objects;

public class Profit {

    private double value;

    public Profit(double value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("[ERROR] 0원 이상의 금액을 배팅해주세요.");
        }
    }

    public void multiply(double multiplier) {
        value = value * multiplier;
    }

    public void update(double newValue) {
        value = newValue;
    }

    public double getValue() {
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
        Profit profit = (Profit) o;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

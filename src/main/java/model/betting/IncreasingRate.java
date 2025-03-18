package model.betting;

import java.util.Objects;

public class IncreasingRate {
    private final double rate;

    public IncreasingRate(double rate) {
        validateRange(rate);
        this.rate = rate;
    }

    public static IncreasingRate whenBlackjackWin() {
        return new IncreasingRate(1.5);
    }

    private void validateRange(double rate) {
        if (rate <= 0) {
            throw new IllegalArgumentException("증가율은 음수가 될 수 없습니다.");
        }
    }

    public int multiplyWith(Money money) {
        return (int) (money.getValue() * rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncreasingRate that = (IncreasingRate) o;
        return Double.compare(rate, that.rate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rate);
    }
}

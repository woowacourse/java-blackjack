package domain;

import java.util.Objects;

public class ResultAmount {

    private final int resultAmount;

    public ResultAmount(int resultAmount) {
        this.resultAmount = resultAmount;
    }

    public int getResultAmount() {
        return resultAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultAmount that = (ResultAmount) o;
        return resultAmount == that.resultAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultAmount);
    }

    public ResultAmount minus(int amount) {
        return new ResultAmount(resultAmount - amount);
    }

}

package blackjack.domain.game;

import java.util.Objects;

public class ResultCount {

    private int value;

    // TODO: 불변객체로 변경
    public ResultCount() {
        value = 0;
    }

    public ResultCount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increment() {
        value++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResultCount that = (ResultCount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ResultCount{" + "value=" + value + '}';
    }
}

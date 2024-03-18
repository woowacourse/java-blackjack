package domain.result;

import java.util.Objects;

public class Income {

    private final int value;

    public Income(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income1 = (Income) o;
        return value == income1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Income{" +
                "income=" + value +
                '}';
    }
}

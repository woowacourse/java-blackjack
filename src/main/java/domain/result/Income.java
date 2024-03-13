package domain.result;

import java.util.Objects;

public class Income {

    private final int income;

    public Income(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income1 = (Income) o;
        return income == income1.income;
    }

    @Override
    public int hashCode() {
        return Objects.hash(income);
    }

    @Override
    public String toString() {
        return "Income{" +
                "income=" + income +
                '}';
    }
}

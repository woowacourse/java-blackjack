package domain.result;

import domain.Name;

import java.util.Map;
import java.util.Objects;

public class Incomes {

    private final Map<Name, Income> incomes;

    public Incomes(Map<Name, Income> incomes) {
        this.incomes = incomes;
    }

    public int getDealerIncome() {
        int sumOfPlayerIncome = incomes.entrySet()
                .stream()
                .mapToInt(income -> income.getValue().getIncome())
                .sum();
        return -sumOfPlayerIncome;
    }

    public Map<Name, Income> getIncomes() {
        return incomes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incomes incomes1 = (Incomes) o;
        return Objects.equals(incomes, incomes1.incomes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomes);
    }

    @Override
    public String toString() {
        return "Incomes{" +
                "incomes=" + incomes +
                '}';
    }
}

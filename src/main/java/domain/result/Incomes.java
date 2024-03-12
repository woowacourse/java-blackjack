package domain.result;

import domain.Name;

import java.util.Map;

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
}

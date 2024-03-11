package domain;

import java.util.Map;

public class Incomes {

    private final Map<Name, Income> incomes;

    public Incomes(Map<Name, Income> incomes) {
        this.incomes = incomes;
    }

    public Income getIncomeByName(Name name) {
        return incomes.get(name);
    }

    public int getDealerIncome() {
        int sumOfPlayerIncome = incomes.entrySet()
                .stream()
                .mapToInt(income -> income.getValue().getIncome())
                .sum();
        return -sumOfPlayerIncome;
    }
}

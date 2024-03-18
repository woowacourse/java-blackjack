package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Incomes {

    private final List<Player> players;
    private final IncomeCalculator incomeCalculator;

    public Incomes(List<Player> players, IncomeCalculator incomeCalculator) {
        this.players = players;
        this.incomeCalculator = incomeCalculator;
    }

    public Income dealerIncome(Dealer dealer) {
        int sumOfPlayerIncome = players.stream()
                .map(player -> incomeCalculator.determineIncome(dealer.decideStatus(player), player.getBetAmount()))
                .mapToInt(Income::getValue)
                .sum();
        return new Income(-sumOfPlayerIncome);
    }

    public Map<Player, Income> playersIncomes(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> incomeCalculator.determineIncome(dealer.decideStatus(player), player.getBetAmount()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incomes incomes = (Incomes) o;
        return Objects.equals(players, incomes.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        return "Incomes{" +
                "players=" + players +
                '}';
    }
}

package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Incomes {

    private final List<Player> players;

    public Incomes(List<Player> players) {
        this.players = players;
    }

    public Income dealerIncome(Dealer dealer) {
        int sumOfPlayerIncome = players.stream()
                .map(player -> player.determineIncome(dealer.decideStatus(player)))
                .mapToInt(Income::getIncome)
                .sum();
        return new Income(-sumOfPlayerIncome);
    }

    public Map<Player, Income> playersIncomes(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.determineIncome(dealer.decideStatus(player))
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

package domain.result;

import domain.participant.BetAmount;
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

    public Incomes(List<Player> players) {
        this.players = players;
    }

    public Income dealerIncome(Dealer dealer) {
        int sumOfPlayerIncome = players.stream()
                .map(player -> determineIncome(dealer.decideStatus(player), player.getBetAmount()))
                .mapToInt(Income::getValue)
                .sum();
        return new Income(-sumOfPlayerIncome);
    }

    public Map<Player, Income> playersIncomes(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        player -> determineIncome(dealer.decideStatus(player), player.getBetAmount()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    public Income determineIncome(Status status, BetAmount betAmount) {
        if (status == Status.WIN) {
            return new Income(betAmount.getValue());
        }
        if (status == Status.LOSE) {
            return new Income(-betAmount.getValue());
        }
        if (status == Status.WIN_BLACKJACK) {
            return new Income((int) Math.round(1.5 * betAmount.getValue()));
        }
        return new Income(0);
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

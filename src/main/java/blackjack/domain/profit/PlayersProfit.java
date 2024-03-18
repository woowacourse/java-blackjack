package blackjack.domain.profit;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class PlayersProfit {

    private final Map<Player, Profit> profits;

    public PlayersProfit(Map<Player, Profit> profits) {
        this.profits = profits;
    }

    public static PlayersProfit createInitial(Map<Player, BetAmount> playersBetAmount) {
        Map<Player, Profit> playersProfit = playersBetAmount.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> new Profit(entry.getValue())
                ));

        return new PlayersProfit(playersProfit);
    }

    public void calculate(Dealer dealer) {
        profits.forEach((player, betAmount) -> {
            double multiplier = PlayerResult.determineMultiplier(player, dealer);
            Profit profit = betAmount.multiply(multiplier);
            profits.put(player, profit);
        });
    }

    public Profit dealerProfit() {
        return playersProfit().multiply(-1);
    }

    private Profit playersProfit() {
        return profits.values().stream()
                .reduce(new Profit(0), Profit::add);
    }

    public Map<Player, Profit> getProfits() {
        return Map.copyOf(profits);
    }
}

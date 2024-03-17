package blackjack.domain.profit;

import blackjack.domain.game.Result;
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

    public static PlayersProfit createInitial(Map<Player, BetAmount> rawProfits) {
        Map<Player, Profit> profits = rawProfits.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> new Profit(entry.getValue())
                ));

        return new PlayersProfit(profits);
    }

    public void calculate(Dealer dealer) {
        profits.forEach((player, betAmount) -> {
            double multiplier = Result.determineMultiplier(player, dealer);
            Profit profit = betAmount.multiply(multiplier);
            profits.put(player, profit);
        });
    }

    public Map<Player, Profit> getProfits() {
        return Map.copyOf(profits);
    }
}

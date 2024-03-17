package blackjack.domain.profit;

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

    public Map<Player, Profit> getProfits() {
        return Map.copyOf(profits);
    }
}

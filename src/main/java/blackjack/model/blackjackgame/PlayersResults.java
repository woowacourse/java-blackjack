package blackjack.model.blackjackgame;

import blackjack.model.participants.Player;
import java.util.Map;

public final class PlayersResults {
    private final Map<Player, Profit> results;

    public PlayersResults(Map<Player, Profit> results) {
        this.results = results;
    }

    public Profit getDealerProfit() {
        return results.values()
                .stream()
                .reduce(Profit.getDefaults(), Profit::sum)
                .reverse();
    }

    public Map<Player, Profit> getResults() {
        return results;
    }
}

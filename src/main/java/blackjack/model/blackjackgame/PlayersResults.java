package blackjack.model.blackjackgame;

import blackjack.model.participants.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PlayersResults {
    private final Map<Player, Profit> results;

    public PlayersResults(final Map<Player, Profit> results) {
        this.results = new HashMap<>(results);
    }

    public Profit getDealerProfit() {
        return results.values()
                .stream()
                .reduce(Profit.getDefaults(), Profit::sum)
                .reverse();
    }

    public Map<Player, Profit> getResults() {
        return Collections.unmodifiableMap(results);
    }
}

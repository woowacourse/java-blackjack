package blackjack.model.blackjackgame;

import blackjack.model.participants.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PlayersBlackjackResults {
    private final Map<Player, Profit> results;

    public PlayersBlackjackResults(final Map<Player, Profit> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public Profit calculateDealerProfit() {
        return results.values()
                .stream()
                .reduce(Profit.getDefaults(), Profit::sum)
                .reverse();
    }

    public Map<Player, Profit> getResults() {
        return Collections.unmodifiableMap(results);
    }
}

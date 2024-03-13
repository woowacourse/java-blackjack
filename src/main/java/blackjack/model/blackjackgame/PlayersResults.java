package blackjack.model.blackjackgame;

import blackjack.model.participants.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PlayersResults {
    private final Map<Player, Profit> results = new LinkedHashMap<>();

    public void add(final Player player, final Profit profit) {
        results.put(player, profit);
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

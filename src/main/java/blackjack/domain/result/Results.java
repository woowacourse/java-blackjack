package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import blackjack.domain.player.Player;

public class Results {

    private final Map<Player, MatchResult> results = new LinkedHashMap<>();

    public void addResult(Player player, Match result) {
        results.put(player, results.getOrDefault(player, new MatchResult()).addMatchResult(result));
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(results.keySet());
    }

    public MatchResult getResult(Player player) {
        return results.get(player);
    }
}

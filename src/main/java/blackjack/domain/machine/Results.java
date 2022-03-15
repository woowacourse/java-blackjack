package blackjack.domain.machine;

import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Results {

    private final Map<Player, MatchResult> results = new LinkedHashMap<>();

    public void addResult(Player player, Match result) {
        results.put(player, results.getOrDefault(player, new MatchResult()).addMatchResult(result));
    }

    public Set<Player> getPlayers() {
        return results.keySet();
    }

    public MatchResult getResult(Player player) {
        return results.get(player);
    }
}

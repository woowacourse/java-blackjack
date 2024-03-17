package blackjack.domain.game;

import blackjack.domain.participant.Player;
import java.util.Map;

public class Result {

    private final Map<Player, Integer> results;

    public Result(Map<Player, Integer> results) {
        this.results = results;
    }

    public int calculateDealerEarning() {
        return results.values().stream()
                .mapToInt(playerEarning -> -playerEarning)
                .sum();
    }

    public Map<Player, Integer> getResults() {
        return results;
    }
}

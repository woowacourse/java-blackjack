package blackjack.domain.game;

import blackjack.domain.participant.Player;
import java.util.Map;

public class Result {

    private final Map<Player, Integer> results;

    public Result(Map<Player, Integer> results) {
        this.results = results;
    }

    public int calculateDealerEarning() {
        int dealerEarning = 0;
        for (int playerEarning : results.values()) {
            dealerEarning -= playerEarning;
        }

        return dealerEarning;
    }

    public Map<Player, Integer> getResults() {
        return results;
    }
}

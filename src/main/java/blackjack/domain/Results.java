package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Map;

public class Results {

    private final OutcomeResults dealerResult;
    private final Map<Player, OutcomeResults> playerResults;

    public Results(OutcomeResults dealerResult, Map<Player, OutcomeResults> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public OutcomeResults getDealerResult() {
        return dealerResult;
    }

    public Map<Player, OutcomeResults> getPlayerResults() {
        return playerResults;
    }
}

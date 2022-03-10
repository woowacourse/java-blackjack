package blackjack.domain;

import java.util.Map;

public class Results {

    private final OutcomeResults dealerResult;
    private final Map<Gamer, OutcomeResults> playerResults;

    public Results(OutcomeResults dealerResult, Map<Gamer, OutcomeResults> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public OutcomeResults getDealerResult() {
        return dealerResult;
    }

    public Map<Gamer, OutcomeResults> getPlayerResults() {
        return playerResults;
    }
}

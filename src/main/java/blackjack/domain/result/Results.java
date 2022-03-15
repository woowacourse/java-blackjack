package blackjack.domain.result;

import blackjack.domain.user.User;
import java.util.Map;

public class Results {

    private final OutcomeResults dealerResult;
    private final Map<User, OutcomeResults> playerResults;

    public Results(OutcomeResults dealerResult, Map<User, OutcomeResults> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public OutcomeResults getDealerResult() {
        return dealerResult;
    }

    public Map<User, OutcomeResults> getPlayerResults() {
        return playerResults;
    }
}

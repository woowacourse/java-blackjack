package blackjack.domain.rule;

import blackjack.domain.bet.Money;
import java.util.List;

public class BetResults {

    private final List<BetResult> betResults;

    public BetResults(List<BetResult> betResults) {
        this.betResults = betResults;
    }

    public Money calculatePlayersEarned() {
        return new Money(betResults.stream()
                .map(BetResult::getEarned)
                .reduce(0, Integer::sum));
    }

    public List<BetResult> getBetResults() {
        return betResults;
    }
}

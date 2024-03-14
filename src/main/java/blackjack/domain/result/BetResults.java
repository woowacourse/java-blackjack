package blackjack.domain.result;

import blackjack.domain.bet.Money;
import java.util.List;

public class BetResults {

    private final List<BetResult> betResults;

    public BetResults(List<BetResult> betResults) {
        this.betResults = betResults;
    }

    public Money calculateDealerProfit() {
        return new Money(betResults.stream()
                .map(BetResult::getProfit)
                .reduce(0, Integer::sum))
                .inverse();
    }

    public List<BetResult> getBetResults() {
        return betResults;
    }
}

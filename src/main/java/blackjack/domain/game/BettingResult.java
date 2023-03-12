package blackjack.domain.game;

import blackjack.domain.user.Player;
import blackjack.domain.vo.Money;
import java.util.Map;

public class BettingResult {
    private final Map<Player, Money> bettingResults;

    public BettingResult(final Map<Player, Money> bettingResults) {
        this.bettingResults = bettingResults;
    }

    public Money getDealerBettingResult() {
        return bettingResults.values().stream()
                .reduce(new Money(0), (Money::minus));
    }

    public Map<Player, Money> getBettingResults() {
        return Map.copyOf(bettingResults);
    }
}

package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;

import java.util.Map;

public class BettingResults {
    private final Map<Participant, Money> results;

    public BettingResults(final Map<Participant, Money> results, final Dealer dealer) {
        validate(results);
        this.results = results;
        this.results.put(dealer, getDealerProfit());
    }

    public Map<Participant, Money> getPlayerBettingResults() {
        return results;
    }

    private Money getDealerProfit() {
        return results.values()
                .stream()
                .reduce(Money.ZERO, Money::spend);
    }

    private void validate(final Map<Participant, Money> bettingResults) {
        if (bettingResults.isEmpty()) {
            throw new IllegalArgumentException("배팅 결과가 비어있을 수 없습니다.");
        }
    }
}

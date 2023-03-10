package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResults {
    private final Map<Participant, Money> results = new LinkedHashMap<>();

    public BettingResults(final Map<Participant, Money> results, final Dealer dealer) {
        validate(results);
        this.results.put(dealer, getDealerProfit(results));
        this.results.putAll(results);
    }

    public Map<Participant, Money> getPlayerBettingResults() {
        return results;
    }

    private Money getDealerProfit(final Map<Participant, Money> results) {
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

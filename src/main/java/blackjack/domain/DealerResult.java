package blackjack.domain;

import java.util.Map;

public class DealerResult {
    private final Map<Match, Integer> dealerMatches;

    public DealerResult(Map<Match, Integer> dealerMatches) {
        this.dealerMatches = dealerMatches;
    }

    public Map<Match, Integer> get() {
        return dealerMatches;
    }
}

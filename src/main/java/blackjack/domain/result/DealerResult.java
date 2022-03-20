package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import java.util.Collections;
import java.util.Map;

public class DealerResult {
    private final Dealer dealer;
    private final Map<Match, Integer> matchResult;

    public DealerResult(Dealer dealer, Map<Match, Integer> matchResult) {
        this.dealer = dealer;
        this.matchResult = matchResult;
    }

    public String getName() {
        return dealer.getName();
    }

    public Map<Match, Integer> getMatchResult() {
        return Collections.unmodifiableMap(matchResult);
    }
}

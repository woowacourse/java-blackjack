package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.util.NullChecker;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameResult {

    private final Map<Gambler, Integer> GamblerResults;
    private final Map<Dealer, Integer> dealerResult;

    public GameResult(Dealer dealer, Gamblers gamblers) {
        NullChecker.validateNotNull(dealer, gamblers);
        this.GamblerResults = Collections
            .unmodifiableMap(calculateGamblerResults(dealer, gamblers));
        this.dealerResult = Collections.unmodifiableMap(calculateDealerResult(dealer));
    }

    private Map<Gambler, Integer> calculateGamblerResults(Dealer dealer, Gamblers gamblers) {
        return gamblers.getGamblers().stream()
            .collect(Collectors
                .toMap(player -> player,
                    player -> player.getProfit(PlayerOutcome.of(player, dealer)),
                    (a1, a2) -> a1, LinkedHashMap::new));
    }

    private Map<Dealer, Integer> calculateDealerResult(Dealer dealer) {
        int GamblerTotalResult = 0;
        for (Integer playerResult : this.GamblerResults.values()) {
            GamblerTotalResult += playerResult;
        }
        Map<Dealer, Integer> result = new HashMap<>();
        result.put(dealer, GamblerTotalResult * -1);
        return result;
    }

    public Map<Gambler, Integer> getGamblerResults() {
        return GamblerResults;
    }

    public Map<Dealer, Integer> getDealerResult() {
        return dealerResult;
    }
}

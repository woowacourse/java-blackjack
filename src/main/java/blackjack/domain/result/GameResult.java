package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.util.NullChecker;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameResult {

    private final Map<Gambler, Integer> playerResults;
    private final int dealerResult;

    public GameResult(Dealer dealer, Gamblers gamblers) {
        NullChecker.validateNotNull(dealer, gamblers);
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(dealer, gamblers));
        this.dealerResult = calculateDealerResults();
    }

    private Map<Gambler, Integer> calculatePlayerResults(Dealer dealer, Gamblers gamblers) {
        return gamblers.getGamblers().stream()
            .collect(Collectors
                .toMap(player -> player,
                    player -> PlayerOutcome.of(player, dealer).getProfit(player),
                    (a1, a2) -> a1, LinkedHashMap::new));
    }

    private int calculateDealerResults() {
        int dealerResult = 0;
        for (Integer playerResult : this.playerResults.values()) {
            dealerResult += playerResult;
        }
        return dealerResult * -1;
    }

    public Map<Gambler, Integer> getPlayerResults() {
        return playerResults;
    }

    public int getDealerResult() {
        return dealerResult;
    }
}

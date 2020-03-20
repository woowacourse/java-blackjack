package blackjack.domain.result;

import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Player;
import blackjack.domain.gambler.Players;
import blackjack.util.NullChecker;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameResult {

    private final Map<Player, Integer> playerResults;
    private final int dealerResult;

    public GameResult(Dealer dealer, Players players) {
        NullChecker.validateNotNull(dealer, players);
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(dealer, players));
        this.dealerResult = calculateDealerResults();
    }

    private Map<Player, Integer> calculatePlayerResults(Dealer dealer, Players players) {
        return players.getPlayers().stream()
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

    public Map<Player, Integer> getPlayerResults() {
        return playerResults;
    }

    public int getDealerResult() {
        return dealerResult;
    }
}

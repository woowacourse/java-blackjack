package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final static Map<Outcome, Integer> initialDealerResults;

    static {
        Map<Outcome, Integer> dealerResults = new LinkedHashMap<>();
        List<Outcome> dealerOutcomes = Arrays.asList(Outcome.values());
        Collections.reverse(dealerOutcomes);
        for (Outcome outcome : dealerOutcomes) {
            dealerResults.put(outcome, 0);
        }
        initialDealerResults = dealerResults;
    }

    private final Map<Player, Outcome> playerResults;
    private final Map<Outcome, Integer> dealerResults;

    public GameResult(Dealer dealer, Players players) {
        this.playerResults = Collections.unmodifiableMap(calculatePlayerResults(dealer, players));
        this.dealerResults = Collections.unmodifiableMap(calculateDealerResults());
    }

    private Map<Player, Outcome> calculatePlayerResults(Dealer dealer, Players players) {
        Map<Player, Outcome> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResults.put(player, player.calculateOutcome(dealer));
        }
        return playerResults;
    }

    private Map<Outcome, Integer> calculateDealerResults() {
        Map<Outcome, Integer> dealerResults = initialDealerResults;
        for (Outcome outcome : this.playerResults.values()) {
            dealerResults.put(outcome, dealerResults.get(outcome) + 1);
        }
        return dealerResults;
    }

    public Map<Player, Outcome> getPlayersResult() {
        return playerResults;
    }

    public Map<Outcome, Integer> getDealerResults() {
        return dealerResults;
    }
}

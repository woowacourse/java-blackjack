package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Outcome> playerResults;
    private final Map<Outcome, Integer> dealerResults;

    public GameResult(Dealer dealer, Players players) {
        Map<Player, Outcome> playerResults = calculatePlayerResults(dealer, players);
        this.playerResults = Collections.unmodifiableMap(playerResults);
        this.dealerResults = Collections.unmodifiableMap(calculateDealerResults());
    }

    private Map<Player, Outcome> calculatePlayerResults(Dealer dealer, Players players) {
        Map<Player, Outcome> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResults.put(player, player.calculateOutcome(dealer.getCards().getScore()));
        }
        return playerResults;
    }

    private Map<Outcome, Integer> calculateDealerResults() {
        Map<Outcome, Integer> dealerResults = new LinkedHashMap<>();
        dealerResults.put(Outcome.WIN, 0);
        dealerResults.put(Outcome.DRAW, 0);
        dealerResults.put(Outcome.LOSE, 0);
        for (Outcome outcome : this.playerResults.values()) {
            Outcome dealerOutcome = Outcome.converseOutcome(outcome);
            dealerResults.put(dealerOutcome, dealerResults.get(dealerOutcome) + 1);
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

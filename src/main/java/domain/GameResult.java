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
        Map<Player, Outcome> playerResults = getPlayerResults(dealer, players);
        this.playerResults = Collections.unmodifiableMap(playerResults);
        this.dealerResults = Collections.unmodifiableMap(getDealerResults(playerResults));
    }

    private Map<Player, Outcome> getPlayerResults(Dealer dealer, Players players) {
        Map<Player, Outcome> playerResults = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResults.put(player, player.calculateOutcome(dealer.getCards().getScore()));
        }
        return playerResults;
    }

    private Map<Outcome, Integer> getDealerResults(Map<Player, Outcome> playerResults) {
        Map<Outcome, Integer> dealerResults = new LinkedHashMap<>();
        dealerResults.put(Outcome.WIN, 0);
        dealerResults.put(Outcome.DRAW, 0);
        dealerResults.put(Outcome.LOSE, 0);
        for (Outcome outcome : playerResults.values()) {
            dealerResults.put(Outcome.converseOutcome(outcome),
                dealerResults.get(Outcome.converseOutcome(outcome)) + 1);
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

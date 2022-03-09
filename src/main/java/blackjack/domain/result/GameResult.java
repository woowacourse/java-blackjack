package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;

import java.util.*;

public class GameResult {

    private final Map<Name, BlackJackResult> playerResults = new HashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        addResults(players, dealer);
    }

    private void addResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName(), result);
        }
    }

    public Map<Name, BlackJackResult> getPlayerResult() {
        return Collections.unmodifiableMap(playerResults);
    }
}

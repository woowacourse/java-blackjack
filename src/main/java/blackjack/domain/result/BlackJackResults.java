package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackResults {
    private final Map<String, Integer> playerResults = new HashMap<>();

    public BlackJackResults(List<Player> players, Dealer dealer) {
        createResults(players, dealer);
    }

    private void createResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            playerResults.put(player.getName(), player.getProfit(dealer));
        }
    }

    public Map<String, Integer> getPlayerResult() {
        return Collections.unmodifiableMap(playerResults);
    }

    public int getDealerResult() {
        int result = 0;
        for (String playerName : playerResults.keySet()) {
            result += BlackJackResult.getReverse(playerResults.get(playerName));
        }
        return result;
    }
}

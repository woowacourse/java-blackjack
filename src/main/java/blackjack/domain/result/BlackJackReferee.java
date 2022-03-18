package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackReferee {
    private final Map<String, Integer> playerResults = new HashMap<>();

    public BlackJackReferee(List<Player> players, Dealer dealer) {
        addResults(players, dealer);
    }

    private void addResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName(), (int) (player.getBettingMoney() * result.getProfitRate()));
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

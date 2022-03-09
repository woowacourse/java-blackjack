package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Name, BlackJackResult> playerResults = new HashMap<>();
    private final Map<BlackJackResult, Integer> dealerResult = new HashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        initDealerResults();
        addResults(players, dealer);
    }

    private void addResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName(), result);
            dealerResult.merge(result.getReverse(), 1, Integer::sum);
        }
    }

    private void initDealerResults() {
        for (BlackJackResult blackJackResult : BlackJackResult.values()) {
            dealerResult.put(blackJackResult, 0);
        }
    }

    public Map<Name, BlackJackResult> getPlayerResult() {
        return Collections.unmodifiableMap(playerResults);
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

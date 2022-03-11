package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackReferee {

    private static final int DEFAULT_COUNT = 0;
    private static final int INCREASE_COUNT = 1;

    private final Map<String, BlackJackResult> playerResults = new HashMap<>();
    private final Map<BlackJackResult, Integer> dealerResult = new HashMap<>();

    public BlackJackReferee(List<Player> players, Dealer dealer) {
        initDealerResults();
        addResults(players, dealer);
    }

    private void initDealerResults() {
        for (BlackJackResult blackJackResult : BlackJackResult.values()) {
            dealerResult.put(blackJackResult, DEFAULT_COUNT);
        }
    }

    private void addResults(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            BlackJackResult result = player.match(dealer);
            playerResults.put(player.getName().getValue(), result);
            dealerResult.merge(result.getReverse(), INCREASE_COUNT, Integer::sum);
        }
    }

    public Map<String, BlackJackResult> getPlayerResult() {
        return Collections.unmodifiableMap(playerResults);
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

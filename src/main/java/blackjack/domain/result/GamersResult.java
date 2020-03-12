package blackjack.domain.result;

import blackjack.domain.gamer.Player;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GamersResult {

    private final Map<BlackJackResult, Integer> dealerResult;
    private final Map<Player, BlackJackResult> playersResult;

    public GamersResult(Map<BlackJackResult, Integer> dealerResult, Map<Player, BlackJackResult> playersResult) {
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
        this.playersResult = Collections.unmodifiableMap(playersResult);
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, BlackJackResult> getPlayersResult() {
        return playersResult;
    }
}
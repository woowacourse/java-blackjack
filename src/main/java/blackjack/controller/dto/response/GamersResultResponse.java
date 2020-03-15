package blackjack.controller.dto.response;

import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;

import java.util.Collections;
import java.util.Map;

public class GamersResultResponse {

    private final Map<BlackJackResult, Integer> dealerResult;
    private final Map<Player, BlackJackResult> playersResult;

    public GamersResultResponse(Map<BlackJackResult, Integer> dealerResult, Map<Player, BlackJackResult> playersResult) {
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
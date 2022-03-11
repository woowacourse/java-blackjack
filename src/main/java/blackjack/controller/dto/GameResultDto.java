package blackjack.controller.dto;

import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.BlackJackReferee;

import java.util.Map;

public class GameResultDto {
    private final Map<String, BlackJackResult> playerResults;
    private final Map<BlackJackResult, Integer> dealerResult;

    public GameResultDto(BlackJackReferee blackJackReferee) {
        this.playerResults = Map.copyOf(blackJackReferee.getPlayerResult());
        this.dealerResult = Map.copyOf(blackJackReferee.getDealerResult());
    }

    public Map<String, BlackJackResult> getPlayerResults() {
        return playerResults;
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }
}

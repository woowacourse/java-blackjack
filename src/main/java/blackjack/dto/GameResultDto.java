package blackjack.dto;

import java.util.Map;

import blackjack.domain.result.BlackJackResult;

public class GameResultDto {

    private final Map<String, BlackJackResult> playerResults;
    private final Map<BlackJackResult, Integer> dealerResult;

    public GameResultDto(Map<String, BlackJackResult> playerResults,
        Map<BlackJackResult, Integer> dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public Map<String, BlackJackResult> getPlayerResults() {
        return playerResults;
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }
}

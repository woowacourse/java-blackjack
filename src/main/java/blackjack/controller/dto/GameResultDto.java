package blackjack.controller.dto;

import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.GameResult;

import java.util.Map;

public class GameResultDto {
    private final Map<String, BlackJackResult> playerResults;
    private final Map<BlackJackResult, Integer> dealerResult;

    public GameResultDto(GameResult gameResult) {
        this.playerResults = Map.copyOf(gameResult.getPlayerResult());
        this.dealerResult = Map.copyOf(gameResult.getDealerResult());
    }

    public Map<String, BlackJackResult> getPlayerResults() {
        return playerResults;
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return dealerResult;
    }
}

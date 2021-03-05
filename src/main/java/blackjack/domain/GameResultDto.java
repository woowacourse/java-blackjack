package blackjack.domain;

import java.util.Map;

public class GameResultDto {

    Map<String, GameResult> playerResult;
    Map<GameResult, Integer> dealerResult;

    public GameResultDto(Map<String, GameResult> playerResult,
        Map<GameResult, Integer> dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public Map<String, GameResult> getPlayerResult() {
        return playerResult;
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }
}

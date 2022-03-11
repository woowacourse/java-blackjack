package blackjack.dto;

import blackjack.domain.GameResult;
import java.util.Map;

public class ScoreResultDto {
    private final Map<GameResult, Integer> dealerResult;
    private final Map<String, GameResult> playersResult;

    private ScoreResultDto(Map<GameResult, Integer> dealerResult,
                           Map<String, GameResult> playersResult) {
        this.dealerResult = dealerResult;
        this.playersResult = playersResult;
    }

    public static ScoreResultDto from(Map<GameResult, Integer> dealerResult,
                                      Map<String, GameResult> playersResult) {
        return new ScoreResultDto(dealerResult, playersResult);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, GameResult> getPlayersResult() {
        return playersResult;
    }
}

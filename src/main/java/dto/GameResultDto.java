package dto;

import domain.MatchResult;

import java.util.Map;

public class GameResultDto {

    private final Map<MatchResult, Integer> dealerResult;
    private final Map<String, MatchResult> playerResult;

    public GameResultDto(Map<MatchResult, Integer> dealerResult, Map<String, MatchResult> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public Map<MatchResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, MatchResult> getPlayerResult() {
        return playerResult;
    }
}

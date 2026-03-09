package dto;

import domain.MatchResult;
import domain.participant.Player;

import java.util.Map;
import java.util.stream.Collectors;

public class GameResultDto {

    private final Map<MatchResult, Integer> dealerResult;
    private final Map<String, MatchResult> playersResult;

    public GameResultDto(Map<MatchResult, Integer> dealerResult, Map<Player, MatchResult> playersResult) {
        this.dealerResult = dealerResult;
        this.playersResult = playersResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        Map.Entry::getValue
                ));    }

    public Map<MatchResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, MatchResult> getPlayersResult() {
        return playersResult;
    }
}

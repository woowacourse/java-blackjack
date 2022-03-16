package blackjack.dto;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.PlayerResult;

import java.util.Map;

public class MatchResultDto {

    private final Map<PlayerResult, Integer> dealerResult;
    private final Map<String, PlayerResult> playerResult;

    private MatchResultDto(final Map<PlayerResult, Integer> dealerResult,
                           final Map<String, PlayerResult> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public static MatchResultDto toDto(final MatchResult result) {
        return new MatchResultDto(result.getDealerResult(), result.getPlayerResult());
    }

    public Map<PlayerResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, PlayerResult> getPlayerResult() {
        return playerResult;
    }
}

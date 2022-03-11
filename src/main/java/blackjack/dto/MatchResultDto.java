package blackjack.dto;

import java.util.Map;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.MatchStatus;

public class MatchResultDto {

    private final Map<MatchStatus, Integer> dealerResult;
    private final Map<String, MatchStatus> playerResult;

    private MatchResultDto(final Map<MatchStatus, Integer> dealerResult,
                           final Map<String, MatchStatus> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public static MatchResultDto toDto(final MatchResult result) {
        return new MatchResultDto(result.getDealerResult(), result.getPlayerResult());
    }

    public Map<MatchStatus, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, MatchStatus> getPlayerResult() {
        return playerResult;
    }
}

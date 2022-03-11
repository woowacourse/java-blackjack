package blackjack.dto;

import java.util.Map;

import blackjack.domain.result.MatchResult;
import blackjack.domain.result.WinningResult;

public class MatchResultDto {

    private final Map<WinningResult, Integer> dealerResult;
    private final Map<String, WinningResult> playerResult;

    private MatchResultDto(final Map<WinningResult, Integer> dealerResult,
                           final Map<String, WinningResult> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public static MatchResultDto toDto(final MatchResult result) {
        return new MatchResultDto(result.getDealerResult(), result.getPlayerResult());
    }

    public Map<WinningResult, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, WinningResult> getPlayerResult() {
        return playerResult;
    }
}

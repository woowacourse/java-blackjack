package domain.result.dto;

import java.util.List;

public record GameResultAnalysis(
        DealerGameResult dealerGameResult,
        List<PlayerGameResult> playerGameResults
) {
    public static GameResultAnalysis of(DealerGameResult dealerGameResult, List<PlayerGameResult> playerGameResults) {
        return new GameResultAnalysis(dealerGameResult, playerGameResults);
    }
}

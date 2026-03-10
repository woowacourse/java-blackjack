package blackjack.dto;

import blackjack.domain.GameResult;
import java.util.List;

public record TotalWinningResult(
        int dealerWin,
        int dealerDraw,
        int dealerLoss,
        List<PlayerGameResult> playersResults
) {
    public static TotalWinningResult from(List<PlayerGameResult> playersGameResult) {
        List<GameResult> gameResults = playersGameResult.stream()
                .map(PlayerGameResult::gameResult)
                .toList();
        return determineDealerWinning(playersGameResult, gameResults);
    }

    private static TotalWinningResult determineDealerWinning(List<PlayerGameResult> playersGameResult,
                                                             List<GameResult> playerGameResults) {
        int dealerWin = (int) playerGameResults.stream()
                .filter(result -> result == GameResult.LOSE)
                .count();
        int dealerDraw = (int) playerGameResults.stream()
                .filter(result -> result == GameResult.DRAW)
                .count();
        int dealerLose = (int) playerGameResults.stream()
                .filter(result -> result == GameResult.WIN)
                .count();
        return new TotalWinningResult(dealerWin, dealerDraw, dealerLose, playersGameResult);
    }
}

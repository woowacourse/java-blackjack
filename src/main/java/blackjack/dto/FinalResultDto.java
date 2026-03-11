package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.DealerResult;
import blackjack.domain.GameResult;
import blackjack.domain.Player;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalResultDto(
        Map<String, GameResult> playerGameResultMap,
        DealerResult dealerResult
) {

    public static FinalResultDto of(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerGameResultMap = new LinkedHashMap<>();
        players.forEach(player ->
                playerGameResultMap.put(player.getNickname(), player.calculateGameResult(dealer)));
        long dealerWinCount = countByGameResult(playerGameResultMap.values(), GameResult.LOSE);
        long dealerDrawCount = countByGameResult(playerGameResultMap.values(), GameResult.DRAW);
        long dealerLoseCount = countByGameResult(playerGameResultMap.values(), GameResult.WIN);
        DealerResult dealerResult = new DealerResult(dealerWinCount, dealerDrawCount, dealerLoseCount);

        return new FinalResultDto(playerGameResultMap, dealerResult);
    }

    private static long countByGameResult(Collection<GameResult> gameResults, GameResult criteriaGameResult) {
        return gameResults.stream()
                .filter(gameResult -> gameResult == criteriaGameResult)
                .count();
    }
}

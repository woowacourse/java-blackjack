package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.judgement.DealerResult;
import blackjack.domain.judgement.GameResult;

import blackjack.domain.participant.Players;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public record FinalResultDto(
        Map<String, GameResult> playerGameResultMap,
        DealerResult dealerResult
) {

    public static FinalResultDto of(Players players, Dealer dealer) {
        Map<String, GameResult> playerGameResultMap = new LinkedHashMap<>();
        players.all().forEach(player ->
                playerGameResultMap.put(player.getName().toString(), player.calculateGameResult(dealer)));
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

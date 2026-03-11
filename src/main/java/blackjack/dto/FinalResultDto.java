package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameResult;
import blackjack.domain.participant.Player;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalResultDto(
    Map<String, GameResult> playerGameResultMap,
    long dealerWinCount,
    long dealerDrawCount,
    long dealerLoseCount
) {

    public static FinalResultDto of(final List<Player> players, final Dealer dealer) {
        final Map<String, GameResult> playerGameResultMap = new LinkedHashMap<>();
        players.forEach(player ->
            playerGameResultMap.put(player.getNickname(), GameResult.calculate(player, dealer)));
        final long dealerWinCount = countDealerWDLByGameResult(playerGameResultMap.values(), GameResult.LOSE);
        final long dealerDrawCount = countDealerWDLByGameResult(playerGameResultMap.values(), GameResult.DRAW);
        final long dealerLoseCount = countDealerWDLByGameResult(playerGameResultMap.values(), GameResult.WIN);

        return new FinalResultDto(playerGameResultMap, dealerWinCount, dealerDrawCount, dealerLoseCount);
    }

    private static long countDealerWDLByGameResult(final Collection<GameResult> gameResults, final GameResult criteriaGameResult) {
        return gameResults.stream()
            .filter(gameResult -> gameResult == criteriaGameResult)
            .count();
    }


}

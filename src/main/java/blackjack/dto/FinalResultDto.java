package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalResultDto(
    Map<String, GameResult> playerGameResultMap,
    long dealerWinCount,
    long dealerDrawCount,
    long dealerLoseCount
) {

    public static FinalResultDto of(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerGameResultMap = new LinkedHashMap<>();
        players.forEach(player ->
            playerGameResultMap.put(player.getNickname(), player.calculateGameResult(dealer)));
        long dealerWinCount = playerGameResultMap.values()
            .stream()
            .filter(gameResult -> gameResult == GameResult.LOSE)
            .count();
        long dealerDrawCount = playerGameResultMap.values()
            .stream()
            .filter(gameResult -> gameResult == GameResult.DRAW)
            .count();
        long dealerLoseCount = playerGameResultMap.values()
            .stream()
            .filter(gameResult -> gameResult == GameResult.WIN)
            .count();

        return new FinalResultDto(playerGameResultMap, dealerWinCount, dealerDrawCount, dealerLoseCount);
    }


}

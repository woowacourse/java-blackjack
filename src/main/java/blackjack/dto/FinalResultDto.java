package blackjack.dto;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record FinalResultDto(
    Map<String, GameResult> playerGameResultMap
) {

    public static FinalResultDto of(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerGameResultMap = new LinkedHashMap<>();
        players.forEach(player ->
            playerGameResultMap.put(player.getNickname(), GameResult.calculate(player, dealer)));

        return new FinalResultDto(playerGameResultMap);
    }

    public long countByGameResult(GameResult criteriaGameResult) {
        return playerGameResultMap.values()
            .stream()
            .filter(gameResult -> gameResult == criteriaGameResult)
            .count();
    }


}

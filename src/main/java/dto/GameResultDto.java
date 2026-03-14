package dto;

import domain.GameResult;
import domain.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record GameResultDto(Map<String, Long> playerWinTieLossResults,
                            long dealerWinTieLossResult) {

    public static GameResultDto from(GameResult gameResult) {
        Map<String, Long> playerResults = convertPlayerResult(gameResult.getPlayerResults());
        long dealerResults = gameResult.getDealerResult();
        return new GameResultDto(playerResults, dealerResults);
    }

    private static Map<String, Long> convertPlayerResult(Map<Player, Long> playerWinTieLossResults) {
        Map<String, Long> playerResults = new LinkedHashMap<>();
        for (Entry<Player, Long> entry : playerWinTieLossResults.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue());
        }
        return playerResults;
    }
}

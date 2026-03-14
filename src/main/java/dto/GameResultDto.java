package dto;

import domain.GameResult;
import domain.Player;
import domain.Profit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record GameResultDto(Map<String, Long> playerWinTieLossResults,
                            long dealerWinTieLossResult) {

    public static GameResultDto from(GameResult gameResult) {
        Map<String, Long> playerProfits = convertPlayerResult(gameResult.getPlayerProfits());
        long dealerProfit = gameResult.getDealerProfit();
        return new GameResultDto(playerProfits, dealerProfit);
    }

    private static Map<String, Long> convertPlayerResult(Map<Player, Profit> playerWinTieLossResults) {
        Map<String, Long> playerResults = new LinkedHashMap<>();
        for (Entry<Player, Profit> entry : playerWinTieLossResults.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue().profit());
        }
        return playerResults;
    }
}

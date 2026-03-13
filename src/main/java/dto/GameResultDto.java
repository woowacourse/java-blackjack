package dto;

import domain.Game;
import domain.Player;
import domain.Result;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record GameResultDto(Map<String, Integer> dealerWinTieLossResult,
                            Map<String, String> playerWinTieLossResults) {

    public static GameResultDto from(Game game) {
        Map<String, Integer> dealerResults = convertDealerResult(game.getDealerWinTieLossResult());
        Map<String, String> playerResults = convertPlayerResult(game.getPlayerWinTieLossResults());

        return new GameResultDto(dealerResults, playerResults);
    }

    private static Map<String, Integer> convertDealerResult(Map<Result, Integer> dealerWinTieLossResults) {
        Map<String, Integer> dealerResults = new LinkedHashMap<>();
        for (Entry<Result, Integer> entry : dealerWinTieLossResults.entrySet()) {
            dealerResults.put(entry.getKey().getName(), entry.getValue());
        }
        return dealerResults;
    }

    private static Map<String, String> convertPlayerResult(Map<Player, Result> playerWinTieLossResults) {
        Map<String, String> playerResults = new LinkedHashMap<>();
        for (Entry<Player, Result> entry : playerWinTieLossResults.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue().getName());
        }
        return playerResults;
    }
}

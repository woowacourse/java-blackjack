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
        Map<Result, Integer> dealerWinTieLossResults = game.getDealerWinTieLossResult();
        Map<String, Integer> dealerResults = new LinkedHashMap<>();
        for (Entry<Result, Integer> entry : dealerWinTieLossResults.entrySet()) {
            dealerResults.put(entry.getKey().getName(), entry.getValue());
        }

        Map<Player, Result> playerWinTieLossResults = game.getPlayerWinTieLossResults();
        Map<String, String> playerResults = new LinkedHashMap<>();
        for (Entry<Player, Result> entry : playerWinTieLossResults.entrySet()) {
            playerResults.put(entry.getKey().getName(), entry.getValue().getName());
        }

        return new GameResultDto(dealerResults, playerResults);
    }
}

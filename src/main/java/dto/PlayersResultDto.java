package dto;

import domain.Player;
import domain.Result;

import java.util.LinkedHashMap;
import java.util.Map;

public record PlayersResultDto(Map<String, String> playerResults) {

    public static PlayersResultDto from(Map<Player, Result> gameResult) {
        Map<String, String> playerResults = new LinkedHashMap<>();
        for (Map.Entry<Player, Result> entry : gameResult.entrySet()) {
            String playerName = entry.getKey().getName().name();
            String result = convertResult(entry.getValue());
            playerResults.put(playerName, result);
        }

        return new PlayersResultDto(playerResults);
    }

    private static String convertResult(Result result) {
        if (result == Result.PLAYER_WIN) {
            return "승";
        }
        if (result == Result.DEALER_WIN) {
            return "패";
        }
        return "무";
    }
}

package dto;

import domain.game.Result;
import domain.player.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public record ResultDto(Map<String, String> gameResults) {

    public static ResultDto from(Map<Player, Result> gameResult) {
        Map<String, String> results = new LinkedHashMap<>();
        for (Map.Entry<Player, Result> entry : gameResult.entrySet()) {
            String playerName = entry.getKey().getName();
            String result = entry.getValue().name();
            results.put(playerName, result);
        }

        return new ResultDto(results);
    }

    public Map<String, Integer> getDealerResult() {
        Map<String, Integer> result = new HashMap<>();
        result.put(Result.DEALER_WIN.name(), count(Result.DEALER_WIN));
        result.put(Result.PLAYER_WIN.name(), count(Result.PLAYER_WIN));
        result.put(Result.PUSH.name(), count(Result.PUSH));
        return result;
    }

    private int count(final Result status) {
        return (int) gameResults.values().stream()
                .filter(result -> result.equals(status.name()))
                .count();
    }

    public Map<String, String> getPlayerResult() {
        Map<String, String> results = new LinkedHashMap<>();
        for (Map.Entry<String, String> result : gameResults.entrySet()) {
            results.put(result.getKey(), result.getValue());
        }
        return results;
    }
}

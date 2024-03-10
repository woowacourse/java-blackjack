package dto;

import domain.game.Score;
import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public record ScoreDto(Map<String, Integer> scores) {
    public static ScoreDto from(Map<Player, Score> gameScores) {
        Map<String, Integer> scores = new LinkedHashMap<>();
        for (Map.Entry<Player, Score> entry : gameScores.entrySet()) {
            String playerName = entry.getKey().getName();
            int score = entry.getValue().get();
            scores.put(playerName, score);
        }

        return new ScoreDto(scores);
    }

    public int getByPlayerName(String playerName) {
        return scores.getOrDefault(playerName, 0);
    }
}

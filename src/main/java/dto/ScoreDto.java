package dto;

import domain.game.Score;
import domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public record ScoreDto(Map<String, Integer> scores) {
    public static ScoreDto from(final Map<Player, Score> gameScores) {
        Map<String, Integer> scores = new LinkedHashMap<>();

        gameScores.forEach((player, score) -> {
            String playerName = player.getName().name();
            int playerScore = score.get();
            scores.put(playerName, playerScore);
        });

        return new ScoreDto(scores);
    }

    public int getByPlayerName(final String playerName) {
        return scores.getOrDefault(playerName, 0);
    }
}

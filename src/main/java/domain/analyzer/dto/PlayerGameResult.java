package domain.analyzer.dto;

import domain.analyzer.GameResult;
import domain.gamer.Player;

public record PlayerGameResult(
        String playerName,
        GameResult gameResult
) {
    public static PlayerGameResult of(Player player, GameResult gameResult) {
        return new PlayerGameResult(player.getMyName(), gameResult);
    }
}

package domain.analyzer.dto;

import domain.GameResult;
import domain.player.Player;

public record PlayerGameResult(
        String playerName,
        GameResult gameResult
) {
    public static PlayerGameResult of(Player player, GameResult gameResult) {
        return new PlayerGameResult(player.toDisplayMyName(), gameResult);
    }
}

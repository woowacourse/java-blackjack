package domain.result.dto;

import domain.participant.Player;
import domain.result.GameResult;

public record PlayerGameResult(String playerName, GameResult gameResult) {
    public static PlayerGameResult of(Player player, GameResult gameResult) {
        return new PlayerGameResult(player.toDisplayMyName(), gameResult);
    }
}

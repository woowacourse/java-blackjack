package domain.result.dto;

import domain.participant.Player;
import domain.result.GameResult;

public record PlayerGameResultDto(String playerName, GameResult gameResult) {
    public static PlayerGameResultDto of(Player player, GameResult gameResult) {
        return new PlayerGameResultDto(player.toDisplayMyName(), gameResult);
    }
}

package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.GameResult;

public record PlayerResultDto(
    String playerName,
    GameResult result
) {

    public static PlayerResultDto of(Player player, GameResult gameResult) {
        return new PlayerResultDto(player.getName(), gameResult);
    }
}

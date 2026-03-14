package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.game.GameResult;

public record GameResultDto(
    String playerName,
    GameResult result
) {
    public static GameResultDto of(Player player, GameResult gameResult) {
        return new GameResultDto(player.getName(), gameResult);
    }
}

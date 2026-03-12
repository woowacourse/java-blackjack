package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.GameResult;

public record GameResultDtos(
    String playerName,
    GameResult result
) {
    public static GameResultDtos of(Player player, GameResult gameResult) {
        return new GameResultDtos(player.getName(), gameResult);
    }
}

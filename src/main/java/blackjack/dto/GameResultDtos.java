package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;

public record GameResultDtos(
    String playerName,
    GameResult result
) {
    public static GameResultDtos of(Player player, GameResult gameResult) {
        return new GameResultDtos(player.getName(), gameResult);
    }
}

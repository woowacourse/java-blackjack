package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;

public record GameResultDto(
    String playerName,
    GameResult result
) {
    public static GameResultDto of(Player player, GameResult gameResult) {
        return new GameResultDto(player.getName(), gameResult);
    }
}

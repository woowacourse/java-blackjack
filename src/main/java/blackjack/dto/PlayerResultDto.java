package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.PlayerBlackjackResult;

public record PlayerResultDto(
    String playerName,
    PlayerBlackjackResult result
) {

    public static PlayerResultDto of(Player player, PlayerBlackjackResult playerBlackjackResult) {
        return new PlayerResultDto(player.getName(), playerBlackjackResult);
    }
}

package blackjack.dto;

import blackjack.model.Player;
import blackjack.model.PlayerBlackjackResult;

public record PlayerResultDto(
    String playerName,
    PlayerBlackjackResult result
) {

    public static PlayerResultDto of(Player player, PlayerBlackjackResult playerBlackjackResult) {
        return new PlayerResultDto(player.getName(), playerBlackjackResult);
//        if (result == PlayerBlackjackResult.WIN) {
//            return new PlayerResultDto(playerName, "승");
//        }
//        if (result == PlayerBlackjackResult.LOSE) {
//            return new PlayerResultDto(playerName, "패");
//        }
//        return new PlayerResultDto(playerName, "푸시");
    }
}

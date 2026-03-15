package blackjack.view.dto;

import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public record PlayerProfit(
        String nickname,
        int profit
) {

    public static PlayerProfit of(Player player, int profit) {
        return new PlayerProfit(
                player.getNickname(),
                profit
        );
    }

    public static List<PlayerProfit> listFrom(Map<Player, Integer> playerProfits) {
        return playerProfits.entrySet().stream()
                .map(playerIntegerEntry ->
                        of(playerIntegerEntry.getKey(), playerIntegerEntry.getValue()))
                .toList();
    }
}

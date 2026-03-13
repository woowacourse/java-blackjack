package blackjack.view.dto;

import blackjack.domain.participant.Player;

public record PlayerProfit(
        String nickname,
        long profit
) {

    public static PlayerProfit of(Player player, long profit) {
        return new PlayerProfit(
                player.getNickname(),
                profit
        );
    }
}

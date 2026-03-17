package blackjack.dto;

import blackjack.domain.participants.Player;

public record PlayerProfitDto(
    String playerName,
    long profit
) {
    public static PlayerProfitDto of(Player player, long profit) {
        return new PlayerProfitDto(player.getName(), profit);
    }
}

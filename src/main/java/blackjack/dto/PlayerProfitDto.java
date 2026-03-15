package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;

public record PlayerProfitDto(
    String playerName,
    long profit
) {
    public static PlayerProfitDto of(Player player, Profit profit) {
        return new PlayerProfitDto(player.getName(), profit.value());
    }
}

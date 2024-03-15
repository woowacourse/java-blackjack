package blackjack.dto;

import blackjack.domain.participant.Player;

public record PlayerProfitResult(String name, String profit) {

    public static PlayerProfitResult of(Player player) {
        return new PlayerProfitResult(player.getName(), player.getProfit());
    }
}

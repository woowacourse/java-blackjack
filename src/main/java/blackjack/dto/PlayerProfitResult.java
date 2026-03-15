package blackjack.dto;

import blackjack.domain.Player;

public record PlayerProfitResult(String playerName, double profit) {

    public static PlayerProfitResult from(Player player, Double profit) {
        return new PlayerProfitResult(player.getName(), profit);
    }
}

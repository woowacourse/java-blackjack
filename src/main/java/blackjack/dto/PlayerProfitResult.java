package blackjack.dto;

import blackjack.domain.Player;
import java.math.BigDecimal;

public record PlayerProfitResult(String playerName, BigDecimal profit) {

    public static PlayerProfitResult from(Player player, BigDecimal profit) {
        return new PlayerProfitResult(player.getName(), profit);
    }
}

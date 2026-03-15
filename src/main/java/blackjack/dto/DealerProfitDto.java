package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;
import java.util.Map;

public record DealerProfitDto(long profit) {
    public static DealerProfitDto from(Map<Player, Profit> playerProfits) {
        long profit = playerProfits.values().stream()
            .mapToLong(playerProfit -> playerProfit.negative().value()).sum();
        return new DealerProfitDto(profit);
    }
}

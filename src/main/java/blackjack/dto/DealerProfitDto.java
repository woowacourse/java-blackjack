package blackjack.dto;

import blackjack.domain.participants.Player;
import java.util.Map;

public record DealerProfitDto(long profit) {
    public static DealerProfitDto from(Map<Player, Long> playerProfits) {
        long playerProfitSum = playerProfits.values().stream()
            .mapToLong(playerProfit -> playerProfit)
            .sum();
        return new DealerProfitDto(calculateDealerProfit(playerProfitSum));
    }

    private static long calculateDealerProfit(long playerProfitSum) {
        return playerProfitSum * -1;
    }
}

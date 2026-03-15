package blackjack.dto;

import blackjack.domain.participants.Player;
import blackjack.domain.participants.Profit;
import java.util.Map;

public record DealerProfitDto(long profit) {
    public static DealerProfitDto from(Map<Player, Profit> playerProfits) {
        long playerProfitSum = playerProfits.values().stream()
            .mapToLong(Profit::value)
            .sum();
        return new DealerProfitDto(calculateDealerProfit(playerProfitSum));
    }

    private static long calculateDealerProfit(long playerProfitSum) {
        return playerProfitSum * -1;
    }
}

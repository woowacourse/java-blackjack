package domain.result;

import java.util.List;

public record BetProfits(
        BetProfit dealerProfit,
        List<BetProfit> betProfits
) {
}

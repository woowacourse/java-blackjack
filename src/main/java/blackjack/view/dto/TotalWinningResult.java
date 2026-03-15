package blackjack.view.dto;

import java.util.List;

public record TotalWinningResult(
        long dealerProfit,
        List<PlayerProfit> playerResults
) {
}

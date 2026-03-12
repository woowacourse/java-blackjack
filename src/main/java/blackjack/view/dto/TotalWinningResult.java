package blackjack.view.dto;

import java.util.List;

public record TotalWinningResult(
        int dealerProfit,
        List<PlayerProfit> playerResults
) {
}

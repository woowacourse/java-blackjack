package blackjack.dto;

import java.util.List;

public record ProfitResult(int dealerProfit, List<PlayerProfitResult> playerProfits) {
}

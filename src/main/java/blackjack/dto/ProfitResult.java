package blackjack.dto;

import java.util.List;

public record ProfitResult(double dealerProfit, List<PlayerProfitResult> playerProfits) {
}

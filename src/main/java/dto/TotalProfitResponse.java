package dto;

import java.util.Map;

public record TotalProfitResponse(
        Map<String, Integer> playerProfits,
        Map<String, Integer> dealerProfit
) {
}

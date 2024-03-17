package blackjack.dto;

import java.util.Map;

public record GamersNetProfitDto(
        int dealerNetProfit,
        Map<String, Integer> playersNetProfit) {
}

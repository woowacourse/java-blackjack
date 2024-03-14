package blackjack.dto;

import java.util.Map;

public record PlayersNetProfitDto(
        Map<String, Integer> playersNetProfit) {
}

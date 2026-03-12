package team.blackjack.service.dto;

import java.util.Map;

public record PayoutResult(
        Integer dealerPayout,
        Map<String, Integer> playerPayouts
) {
}

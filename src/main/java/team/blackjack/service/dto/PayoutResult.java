package team.blackjack.service.dto;

import java.util.Map;

public record PayoutResult(
        String dealerPayout,
        Map<String, String> playerPayouts
) {
}

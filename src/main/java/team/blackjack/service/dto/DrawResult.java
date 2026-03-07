package team.blackjack.service.dto;

import java.util.List;
import java.util.Map;

public record DrawResult(
        List<String> playerNames,
        String dealerCard,
        Map<String, List<String>> playerCards
) {
}

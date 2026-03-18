package team.blackjack.service.dto;

import java.util.LinkedHashMap;
import java.util.List;

public record DrawResult(
        List<String> playerNames,
        String dealerCard,
        LinkedHashMap<String, List<String>> playerCards
) {
}

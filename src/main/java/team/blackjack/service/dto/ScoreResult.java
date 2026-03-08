package team.blackjack.service.dto;

import java.util.List;
import java.util.Map;

public record ScoreResult(
        List<String> dealerCards,
        int dealerScore,
        List<String> playerNames,
        Map<String, List<String>> playerCards,
        Map<String, Integer> playerScores
) {
}

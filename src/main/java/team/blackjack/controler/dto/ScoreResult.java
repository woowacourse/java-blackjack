package team.blackjack.controler.dto;

import java.util.List;
import java.util.Map;

public record ScoreResult(
        List<String> dealerCard,
        int dealerScore,
        List<String> playerNames,
        Map<String, List<String>> playerCards,
        Map<String, Integer> playerScores
) {
}

package presentation.dto;

import java.util.Map;

public record GameResult(
        int dealerAmount,
        Map<String, Integer> playerAmounts
) {
}

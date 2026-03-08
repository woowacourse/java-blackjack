package application.dto;

import java.util.Map;

public record GameResult(
        int dealerWinAmount,
        int dealerLoseAmount,
        Map<String, domain.RoundResult> playerResults
) {
}

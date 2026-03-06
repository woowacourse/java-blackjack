package team.blackjack.control.dto;

import java.util.Map;

public record GameResult(
        int dealerWinCount,
        int dealerFailCount,
        Map<String, Boolean> WinResults
) {
}

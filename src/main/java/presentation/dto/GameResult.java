package presentation.dto;

import domain.vo.RoundResult;
import java.util.Map;

public record GameResult(
        int dealerWinAmount,
        int dealerLoseAmount,
        Map<String, RoundResult> playerResults
) {
}

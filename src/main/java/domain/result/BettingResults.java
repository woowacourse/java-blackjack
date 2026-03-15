package domain.result;

import java.util.List;

public record BettingResults (
        BettingResult dealerBettingResult,
        List<BettingResult> playerBettingResults
) {
}

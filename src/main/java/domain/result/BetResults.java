package domain.result;

import java.util.List;

public record BetResults(
        BetResult dealerResult,
        List<BetResult> betResults
) {
}

package domain.result;

import java.util.List;

public record GameResults(
        DealerResult dealerResult,
        List<PlayerResult> playerResults
) {
}

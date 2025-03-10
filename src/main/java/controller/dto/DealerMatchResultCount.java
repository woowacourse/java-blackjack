package controller.dto;

import domain.MatchResult;
import java.util.Map;

public record DealerMatchResultCount(
        Map<MatchResult, Integer> matchResultCount
) {

    public static DealerMatchResultCount from(Map<MatchResult, Integer> dealerResults) {
        return new DealerMatchResultCount(dealerResults);
    }
}

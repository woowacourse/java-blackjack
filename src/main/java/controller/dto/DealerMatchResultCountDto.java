package controller.dto;

import domain.stats.MatchResult;
import java.util.Map;

public record DealerMatchResultCountDto(
        Map<MatchResult, Integer> matchResultCount
) {

    public static DealerMatchResultCountDto from(Map<MatchResult, Integer> dealerResults) {
        return new DealerMatchResultCountDto(dealerResults);
    }
}

package controller.dto;

import domain.MatchResult;
import java.util.Map;

public record DealerMatchResultCount(
        Map<MatchResult, Integer> matchResultCount
) {
    
}

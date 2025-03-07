package domain.dto;

import domain.MatchResult;
import java.util.Map;

public record DealerResult(
        String name,
        Map<MatchResult, Integer> matchResultCount
) {

}

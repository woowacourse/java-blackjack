package dto;

import domain.MatchResult;

public record MatchResultLog(
        String name,
        MatchResult matchResult
) {
}

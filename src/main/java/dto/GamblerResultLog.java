package dto;

import domain.MatchResult;

public record GamblerResultLog(
        String name,
        MatchResult matchResult
) {
}

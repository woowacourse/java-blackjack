package domain.dto;

import domain.MatchCase;

import java.util.Map;

public record BlackjackResultDto(
        int winCount,
        int drawCount,
        int loseCount,
        Map<String, MatchCase> matchResultMap
) {
}

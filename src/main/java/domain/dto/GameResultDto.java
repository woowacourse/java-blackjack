package domain.dto;

import domain.MatchResult;
import java.util.EnumMap;
import java.util.Map;

public record GameResultDto(
        EnumMap<MatchResult, Integer> dealerMatchResult,
        Map<String, MatchResult> playerMatchResults
) {
}

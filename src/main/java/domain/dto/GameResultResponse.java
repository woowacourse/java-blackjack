package domain.dto;

import domain.MatchResult;
import java.util.EnumMap;
import java.util.List;

public record GameResultResponse(
        EnumMap<MatchResult, Integer> dealerMatchResult,
        List<PlayerMatchResult> playerMatchResults
) {
}

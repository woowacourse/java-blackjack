package domain.dto;

import domain.MatchResult;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record GameResultResponse(
        Map<MatchResult, Integer> dealerMatchResult,
        List<PlayerMatchResult> playerMatchResults
) {
    public GameResultResponse {
        dealerMatchResult = Collections.unmodifiableMap(new EnumMap<>(dealerMatchResult));
        playerMatchResults = List.copyOf(playerMatchResults);
    }
}

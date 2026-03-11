package team.blackjack.service.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import team.blackjack.domain.Result;

public record MatchResult(
        long dealerWinCount,
        long dealerLoseCount,
        long dealerDrawCount,
        Map<String, Result> playerResultMap
) {
}

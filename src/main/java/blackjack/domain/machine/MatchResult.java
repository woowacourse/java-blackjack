package blackjack.domain.machine;

import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {

    private final Map<Match, Integer> match = new LinkedHashMap<>();

    public MatchResult addMatchResult(Match result) {
        match.put(result, match.getOrDefault(result, 0) + 1);
        return this;
    }

    public Map<Match, Integer> getMatch() {
        return match;
    }
}

package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {

    private static final int POINT = 1;
    private static final int INIT_POINT = 0;

    private final Map<Match, Integer> match = new LinkedHashMap<>();

    public MatchResult addMatchResult(Match result) {
        match.put(result, match.getOrDefault(result, INIT_POINT) + POINT);
        return this;
    }

    public Map<Match, Integer> getMatch() {
        return match;
    }
}

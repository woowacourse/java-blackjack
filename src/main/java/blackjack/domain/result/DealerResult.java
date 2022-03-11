package blackjack.domain.result;

import java.util.Collections;
import java.util.Map;

public class DealerResult {
    private final String name;
    private final Map<Match, Integer> matchResult;

    public DealerResult(String name, Map<Match, Integer> matchResult) {
        this.name = name;
        this.matchResult = matchResult;
    }

    public String getName() {
        return name;
    }

    public Map<Match, Integer> getMatchResult() {
        return Collections.unmodifiableMap(matchResult);
    }
}

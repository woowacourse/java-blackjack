package blackjack.domain.result;

import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<MatchResult, Integer> result;

    public DealerResult() {
        result = new EnumMap<>(MatchResult.class);
        result.put(MatchResult.WIN, 0);
        result.put(MatchResult.LOSE, 0);
        result.put(MatchResult.TIE, 0);
    }

    public void add(MatchResult dealerMatch) {
        result.put(dealerMatch, result.get(dealerMatch) + 1);
    }

    public int getWinCount() {
        return result.get(MatchResult.WIN);
    }

    public int getLoseCount() {
        return result.get(MatchResult.LOSE);
    }

    public int getTieCount() {
        return result.get(MatchResult.TIE);
    }
}

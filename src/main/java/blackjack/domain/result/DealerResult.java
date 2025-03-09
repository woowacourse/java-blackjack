package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResult {

    private final Map<GameResultType, Integer> countsOfResultTypes;
    private final int value;

    public DealerResult(int value) {
        this.countsOfResultTypes = new LinkedHashMap<>();
        this.value = value;
    }

    public void addCountOf(GameResultType gameResultType) {
        countsOfResultTypes.merge(gameResultType, 1, Integer::sum);
    }

    public int getValue() {
        return value;
    }

    public Map<GameResultType, Integer> getCountsOfResultTypes() {
        return Collections.unmodifiableMap(countsOfResultTypes);
    }
}

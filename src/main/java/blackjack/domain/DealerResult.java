package blackjack.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {
    private final Map<GameResultType, Integer> dealerResult;

    private DealerResult() {
        this.dealerResult = new EnumMap<>(GameResultType.class);
    }

    public static DealerResult create() {
        return new DealerResult();
    }

    public void addCountOf(GameResultType gameResultType) {
        dealerResult.merge(gameResultType, 1, Integer::sum);
    }

    public Map<GameResultType, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

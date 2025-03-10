package blackjack.domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<GameResultType, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new EnumMap<>(GameResultType.class);
    }

    public void addCountOf(GameResultType gameResultType) {
        dealerResult.merge(gameResultType, 1, Integer::sum);
    }

    public Map<GameResultType, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

package blackjack.domain.result;

import blackjack.domain.ResultType;

import java.util.Collections;
import java.util.Map;

public class DealerResult {
    private final Map<ResultType, Integer> dealerResult;
    private final long profit;

    DealerResult(Map<ResultType, Integer> dealerResult, long profit) {
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
        this.profit = profit;
    }

    public int get(ResultType resultType) {
        return dealerResult.get(resultType);
    }

    public long getProfit() {
        return this.profit;
    }
}

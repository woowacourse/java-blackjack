package blackjack.domain.result;

import blackjack.domain.ResultType;

import java.util.Collections;
import java.util.Map;

public class DealerResult {
    private final Map<ResultType, Integer> dealerResult;
    private final double profit;

    DealerResult(Map<ResultType, Integer> dealerResult, double profit) {
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
        this.profit = profit;
    }

    public int getCountOfResultOf(ResultType resultType) {
        return dealerResult.get(resultType);
    }

    public double getProfit() {
        return this.profit;
    }
}

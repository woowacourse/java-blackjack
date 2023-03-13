package blackjack.domain.participant;

import blackjack.domain.Result;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class DealerResult {

    private final Map<Result, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new EnumMap<>(Result.class);
    }

    public void calculateDealerResult(final Result result) {
        final Result resultForDealer = changeResultForDealer(result);
        dealerResult.put(resultForDealer, dealerResult.getOrDefault(resultForDealer, 0) + 1);
    }

    private Result changeResultForDealer(final Result result) {
        if (result.equals(Result.WIN)) {
            return Result.LOSE;
        }
        if (result.equals(Result.LOSE)) {
            return Result.WIN;
        }
        return Result.PUSH;
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Integer getValue(Result result) {
        return dealerResult.get(result);
    }
}

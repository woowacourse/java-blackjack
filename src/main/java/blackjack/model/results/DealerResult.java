package blackjack.model.results;

import java.util.Map;

public class DealerResult {
    private final Map<Result, Long> dealerResult;

    public DealerResult(Map<Result, Long> dealerResult) {
        this.dealerResult = dealerResult;
    }

    public Map<Result, Long> getDealerResult() {
        return dealerResult;
    }
}

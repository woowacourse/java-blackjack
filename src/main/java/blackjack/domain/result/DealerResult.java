package blackjack.domain.result;

import blackjack.domain.result.Result;
import java.util.Map;

public class DealerResult {

    private final Map<Result, Integer> result;

    public DealerResult(final Map<Result, Integer> result) {
        this.result = result;
    }

    public Map<Result, Integer> getResult() {
        return result;
    }
}

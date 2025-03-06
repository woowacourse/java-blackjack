package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DealerResult {
    private final Map<GameResult, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new HashMap<>();
    }

    public void add(GameResult gameResult) {
        int value = dealerResult.getOrDefault(gameResult, 0) + 1;
        dealerResult.put(gameResult, value);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return dealerResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DealerResult that = (DealerResult) o;
        return Objects.equals(dealerResult, that.dealerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dealerResult);
    }
}

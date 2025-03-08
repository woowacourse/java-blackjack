package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DealerResult extends Dealer implements ParticipantResult {

    private final Map<GameResult, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new HashMap<>();
    }

    @Override
    public void add(GameResult gameResult) {
        dealerResult.put(gameResult, dealerResult.getOrDefault(gameResult, 0) + 1);
    }


    @Override
    public Map<GameResult, Integer> get() {
        return Collections.unmodifiableMap(dealerResult);
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

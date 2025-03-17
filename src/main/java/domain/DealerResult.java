package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DealerResult {
    private final Map<BlackJackWinningStatus, Integer> dealerResult;

    public DealerResult() {
        this.dealerResult = new HashMap<>();
    }

    public void add(BlackJackWinningStatus blackJackWinningStatus) {
        int value = dealerResult.getOrDefault(blackJackWinningStatus, 0) + 1;
        dealerResult.put(blackJackWinningStatus, value);
    }

    public Map<BlackJackWinningStatus, Integer> getDealerResult() {
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

package domain.result;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class DealerResult {
    private final Map<Result, Integer> dealerResult;

    public DealerResult(final Map<Result, Integer> dealerResult) {
        validateCountPositive(dealerResult);
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
    }

    private void validateCountPositive(Map<Result, Integer> dealerResult) {
        boolean hasNegativeCount = dealerResult.entrySet()
            .stream()
            .anyMatch(entry -> entry.getValue() < 0);

        if (hasNegativeCount) {
            throw new IllegalArgumentException("횟수가 음수일 수 없습니다.");
        }
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DealerResult) {
            return this.dealerResult.equals(((DealerResult) o).dealerResult);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealerResult);
    }
}

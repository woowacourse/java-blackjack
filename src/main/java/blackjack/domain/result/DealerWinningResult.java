package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public final class DealerWinningResult {

    private final Map<String, ResultStatus> result;

    public DealerWinningResult(final Map<String, ResultStatus> result) {
        this.result = new HashMap<>(result);
    }

    public int countResultStatus(final ResultStatus input) {
        return (int) result.values().stream()
                .filter(resultStatus -> resultStatus == input)
                .count();
    }

    public Map<String, ResultStatus> makePlayerWinningResult() {
        return result.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> entry.getValue().makeOppositeResult()));
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final DealerWinningResult that)) {
            return false;
        }
        return Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }
}

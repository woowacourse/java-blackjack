package blackjack.dto;

import blackjack.domain.ResultType;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<String, Integer> results;

    public GameResult(Map<String, Integer> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public int getDealerProfit() {
        return (-1) * results.values().stream().reduce(0, Integer::sum);
    }

    public Map<String, Integer> unwrap() {
        return new LinkedHashMap<>(results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResult that = (GameResult) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}

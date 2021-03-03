package blackjack.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<Player, Result> results;

    public GameResult(Map<Player, Result> results) {
        this.results = results;
    }


    public Map<Result, Integer> getStatistics() {
        Map<Result, Integer> result = new HashMap<>();
        results.values().stream()
            .forEach(value -> result.put(value, result.getOrDefault(result, 0) + 1));

        return result;
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

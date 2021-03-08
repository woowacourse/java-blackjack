package blackjack.dto;

import blackjack.domain.ResultType;
import blackjack.domain.participants.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<Player, ResultType> results;

    public GameResult(Map<Player, ResultType> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public Map<ResultType, Integer> getStatistics() {
        Map<ResultType, Integer> result = new LinkedHashMap<>();
        results.values()
            .forEach(resultType -> result.put(resultType, result.getOrDefault(resultType, 0) + 1));

        return result;
    }

    public Map<Player, ResultType> unwrap() {
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

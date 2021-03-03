package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<Player, ResultType> results;

    public GameResult(Map<Player, ResultType> results) {
        this.results = new HashMap<>(results);
    }


    public Map<ResultType, Integer> getStatistics() {
        Map<ResultType, Integer> result = new HashMap<>();
        results.values().stream()
            .forEach(resultType -> result.put(resultType, result.getOrDefault(resultType, 0) + 1));

        return result;
    }

    public Map<Player, ResultType> unwrap() {
        return new HashMap<>(results);
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

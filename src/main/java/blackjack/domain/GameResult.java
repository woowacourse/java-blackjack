package blackjack.domain;

import blackjack.domain.participant.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GameResult {

    private final Map<Player, ResultType> results;

    public GameResult(Map<Player, ResultType> results) {
        this.results = new LinkedHashMap<>(results);
    }

    public Map<ResultType, Integer> getDealerStatistics() {
        Map<ResultType, Integer> resultStatistics = new HashMap<>();
        this.results.values()
                .forEach(resultType -> resultStatistics.put(
                        resultType.opposite(), resultStatistics.getOrDefault(resultType.opposite(), 0) + 1));

        return resultStatistics;
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

package blackjack.model;

import java.util.Map;

public class TotalResult {

    private final Map<Player, Result> results;

    public TotalResult(Map<Player, Result> results) {
        if (results == null) {
            throw new IllegalArgumentException("results가 null입니다.");
        }

        this.results = results;
    }

    public String getDealerResult() {
        return String.format(
                "%d승 %d무 %d패",
                countOf(Result.WIN), countOf(Result.DRAW), countOf(Result.LOSE)
        );
    }

    private long countOf(Result target) {
        return results.values().stream()
                .filter(result -> result == target)
                .count();
    }
}

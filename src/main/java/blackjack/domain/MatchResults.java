package blackjack.domain;

import java.util.List;

public class MatchResults {
    private final List<ResultType> results;

    public MatchResults(final List<ResultType> results) {
        this.results = results;
    }

    public MatchResults(final ResultType results) {
        this(List.of(results));
    }

    public List<ResultType> getResults() {
        return results;
    }
}

package blackjack.domain.result;

import java.util.List;

public class Results {

    private final List<Result> results;

    public Results(List<Result> results) {
        this.results = results;
    }

    public void add(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}

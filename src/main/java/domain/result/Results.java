package domain.result;

import java.util.ArrayList;
import java.util.List;

public record Results(List<Result> results) {

    public Results() {
        this(List.of());
    }

    public Results(List<Result> results) {
        this.results = List.copyOf(results);
    }

    public Results addResult(Result result) {
        List<Result> results = new ArrayList<>(this.results);
        results.add(result);
        return new Results(results);
    }
}

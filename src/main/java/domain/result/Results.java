package domain.result;

import domain.Dealer;
import domain.betting.Bettings;
import java.util.ArrayList;
import java.util.List;

public record Results(List<Result> results) {

    public Results() {
        this(List.of());
    }

    public Results(List<Result> results) {
        this.results = List.copyOf(results);
    }

    public static Results of(Dealer dealer, Bettings bettings) {
        List<Result> results = bettings.bettings().stream()
                .map(betting -> Result.of(dealer, betting))
                .toList();
        return new Results(results);
    }

    public Results addResult(Result result) {
        List<Result> results = new ArrayList<>(this.results);
        results.add(result);
        return new Results(results);
    }
}

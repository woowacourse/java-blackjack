package blackjack.model;

import java.util.List;

public final class Results {
    private final List<Result> values;

    public Results(List<Result> values) {
        this.values = List.copyOf(values);
    }

    public int countDealerWin() {
        return (int) values.stream()
                .filter(result -> !result.isWin())
                .count();
    }

    public int getSize() {
        return this.values.size();
    }

    public List<Result> getValues() {
        return values;
    }
}

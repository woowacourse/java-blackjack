package blackjack.domain;

import java.util.Map;

public class ProfitResult {
    private final Map<String, Integer> profits;

    public ProfitResult(Map<String, Integer> profits) {
        this.profits = profits;
    }

    public Map<String, Integer> get() {
        return profits;
    }
}

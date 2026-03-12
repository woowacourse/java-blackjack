package domain;

import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {
    private final Map<Outcome, Integer> result;

    public ResultCalculator() {
        this.result = new HashMap<>();
        for (Outcome outcome : Outcome.values()) {
            result.put(outcome, 0);
        }
    }

    public int getCount(Outcome playerOutcome) {
        return result.get(playerOutcome);
    }

    public void addResult(Outcome playerOutcome) {
        result.put(playerOutcome, result.getOrDefault(playerOutcome, 0) + 1);
    }
}

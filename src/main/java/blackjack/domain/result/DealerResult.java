package blackjack.domain.result;

import java.util.List;
import java.util.Map;

public class DealerResult {
    public static final double NEGATIVE_VALUE = -1.0;
    private final String name;
    private final Map<GameResult, List<ScoreResult>> result;

    public DealerResult(String name, Map<GameResult, List<ScoreResult>> result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public double calculateEarnings() {
        return NEGATIVE_VALUE * result.entrySet()
                .stream()
                .flatMapToDouble(entry -> entry.getValue()
                        .stream()
                        .mapToDouble(ScoreResult::calculateEarnings)
                ).sum();
    }

    public Map<GameResult, List<ScoreResult>> getResult() {
        return result;
    }
}

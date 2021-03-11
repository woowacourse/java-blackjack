package blackjack.domain.result;

import java.util.Map;

public class DealerResult {
    private final String name;
    private final Map<GameResult, Long> result;

    public DealerResult(String name, Map<GameResult, Long> result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Map<GameResult, Long> getResult() {
        return result;
    }
}

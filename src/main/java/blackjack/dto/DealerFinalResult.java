package blackjack.dto;

import blackjack.domain.result.GameResult;

import java.util.Map;

public class DealerFinalResult implements FinalResult<Map<GameResult, Long>> {

    private final String name;
    private final Map<GameResult, Long> results;

    public DealerFinalResult(final String name, final Map<GameResult, Long> results) {
        this.name = name;
        this.results = Map.copyOf(results);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<GameResult, Long> getResult() {
        return results;
    }
}

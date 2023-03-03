package blackjack.dto;

import blackjack.domain.GameResult;
import java.util.List;

public class DealerGameResultResponse {
    private final List<GameResult> results;

    public DealerGameResultResponse(List<GameResult> results) {
        this.results = results;
    }

    public List<GameResult> getResults() {
        return results;
    }
}

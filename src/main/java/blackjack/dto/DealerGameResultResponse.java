package blackjack.dto;

import java.util.List;

public class DealerGameResultResponse {
    private final List<String> results;

    public DealerGameResultResponse(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}

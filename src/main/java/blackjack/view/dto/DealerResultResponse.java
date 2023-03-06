package blackjack.view.dto;

import blackjack.domain.participant.Result;
import java.util.Map;

public class DealerResultResponse {

    private final String name;
    private final Map<Result, Integer> result;

    public DealerResultResponse(final String name, final Map<Result, Integer> result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Map<Result, Integer> getResult() {
        return result;
    }
}

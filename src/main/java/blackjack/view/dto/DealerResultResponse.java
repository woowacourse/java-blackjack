package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.result.Result;
import java.util.Map;

public class DealerResultResponse {

    private final String name;
    private final Map<Result, Integer> resultMap;

    private DealerResultResponse(final String name, final Map<Result, Integer> resultMap) {
        this.name = name;
        this.resultMap = resultMap;
    }

    public static DealerResultResponse of(final Dealer dealer, final Map<Result, Integer> resultMap) {
        return new DealerResultResponse(dealer.getName(), resultMap);
    }

    public String getName() {
        return name;
    }

    public Map<Result, Integer> getResultMap() {
        return resultMap;
    }
}

package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerResultResponse {

    private final String name;
    private final Map<String, Integer> result;

    private DealerResultResponse(final String name, final Map<String, Integer> result) {
        this.name = name;
        this.result = result;
    }

    public static DealerResultResponse of(final Dealer dealer, final List<PlayerResultResponse> playerResults) {
        final Map<String, Integer> dealerResult = initResult();
        for (final PlayerResultResponse playerResult : playerResults) {
            final Result result = playerResult.getResult().reverse();
            dealerResult.put(result.getName(), dealerResult.get(result.getName()) + 1);
        }
        return new DealerResultResponse(dealer.getName(), dealerResult);
    }

    private static Map<String, Integer> initResult() {
        final Map<String, Integer> initResult = new HashMap<>();
        for (final Result result : Result.values()) {
            initResult.put(result.getName(), 0);
        }
        return initResult;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getResult() {
        return result;
    }
}

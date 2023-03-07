package blackjack.response;

import java.util.Map;

public class FinalResultResponse {

    private final Map<String, ResultTypeResponse> playersToResult;
    private final Map<ResultTypeResponse, Long> dealerResult;

    public FinalResultResponse(final Map<String, ResultTypeResponse> playersToResult,
            final Map<ResultTypeResponse, Long> dealerResult) {
        this.playersToResult = playersToResult;
        this.dealerResult = dealerResult;
    }

    public Map<String, ResultTypeResponse> getPlayersToResult() {
        return playersToResult;
    }

    public Map<ResultTypeResponse, Long> getDealerResult() {
        return dealerResult;
    }
}

package blackjack.response;

import blackjack.domain.ResultType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalResultResponse {

    private final Map<String, ResultTypeResponse> playersToResult;
    private final Map<ResultTypeResponse, Long> dealerResult;

    private FinalResultResponse(final Map<String, ResultTypeResponse> playersToResult,
            final Map<ResultTypeResponse, Long> dealerResult) {
        this.playersToResult = playersToResult;
        this.dealerResult = dealerResult;
    }

    public static FinalResultResponse from(final Map<String, ResultType> playersToResult) {
        final Map<String, ResultTypeResponse> playersToResultResponse = generatePlayersResult(playersToResult);
        final Map<ResultTypeResponse, Long> dealerResult = generateDealerResult(playersToResult);
        return new FinalResultResponse(playersToResultResponse, dealerResult);
    }

    private static LinkedHashMap<String, ResultTypeResponse> generatePlayersResult(
            final Map<String, ResultType> playersToResult) {
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        result -> ResultTypeResponse.from(result.getValue().getPlayerResultType()),
                        (x, y) -> y,
                        LinkedHashMap::new));
    }

    private static Map<ResultTypeResponse, Long> generateDealerResult(final Map<String, ResultType> playersToResult) {
        return playersToResult.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        result -> ResultTypeResponse.from(result.getValue()),
                        Collectors.counting()));
    }

    public Map<String, ResultTypeResponse> getPlayersToResult() {
        return playersToResult;
    }

    public Map<ResultTypeResponse, Long> getDealerResult() {
        return dealerResult;
    }
}

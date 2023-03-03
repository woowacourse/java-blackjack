package blackjack.dto;

import blackjack.domain.ResultType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalResultDto {
    private final Map<String, ResultType> playersResult;
    private final Map<ResultType, Long> dealerResult;

    public FinalResultDto(final Map<String, ResultType> playerNameToResultType) {
        dealerResult = calculateDealerResult(playerNameToResultType);
        playersResult = createPlayerResult(playerNameToResultType);
    }

    private Map<ResultType, Long> calculateDealerResult(final Map<String, ResultType> playerNameToResultType) {
        return playerNameToResultType.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.counting()));
    }

    private Map<String, ResultType> createPlayerResult(final Map<String, ResultType> playerNameToResultType) {
        return playerNameToResultType.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, (var result) -> result.getValue()
                        .getPlayerResultType(), (x, y) -> y, LinkedHashMap::new));
    }

    private int calculateDealerWinCount(final Map<String, ResultType> playerNameToResultType) {
        return (int) playerNameToResultType.values()
                .stream()
                .filter(resultType -> resultType == ResultType.WIN)
                .count();
    }

    private int calculateDealerLoseCount(final Map<String, ResultType> playerNameToResultType) {
        return (int) playerNameToResultType.values()
                .stream()
                .filter(resultType -> resultType == ResultType.LOSE)
                .count();
    }

    private int calculateDealerTieCount(final Map<String, ResultType> playerNameToResultType) {
        return (int) playerNameToResultType.values()
                .stream()
                .filter(resultType -> resultType == ResultType.TIE)
                .count();
    }


    public Map<String, ResultType> getPlayersResult() {
        return playersResult;
    }

    public Map<ResultType, Long> getDealerResult() {
        return dealerResult;
    }
}

package blackjack.domain.dto;

import blackjack.domain.ResultType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class DealerResult {
    private final Map<ResultType, Integer> dealerResult;

    private DealerResult(Map<ResultType, Integer> dealerResult) {
        this.dealerResult = Collections.unmodifiableMap(dealerResult);
    }

    public static DealerResult of(Results results) {
        return Arrays.stream(ResultType.values()).parallel()
                .collect(collectingAndThen(toMap(
                        ResultType::reverse,
                        results::count
                ), DealerResult::new));
    }

    public int get(ResultType resultType) {
        return dealerResult.get(resultType);
    }
}

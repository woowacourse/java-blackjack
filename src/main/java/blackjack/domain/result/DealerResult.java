package blackjack.domain.result;

import blackjack.domain.participant.Name;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DealerResult {
    private static final String NULL_ERR_MSG = "생성자에 Null이 들어올 수 없습니다.";
    private static final String DELIMITER = " ";

    private final Name name;
    private final Map<ResultType, Integer> dealerResult;

    public DealerResult(Name name, Map<ResultType, Integer> dealerResult) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        Objects.requireNonNull(dealerResult, NULL_ERR_MSG);
        this.name = name;
        this.dealerResult = dealerResult;
    }

    public String showDealerRecord() {
        return Arrays.stream(ResultType.values())
                .map(type -> dealerResult.get(type) + type.getWord())
                .collect(Collectors.joining(DELIMITER));
    }

    public String name() {
        return name.getName();
    }
}

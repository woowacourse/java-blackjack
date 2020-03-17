package blackjack.domain.result;

import blackjack.domain.participant.Name;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static blackjack.domain.card.Card.NULL_ERR_MSG;


public class DealerResult {
    private static final String DELIMITER = " ";
    static final String EMPTY_ERR_MSG = "딜러 결과가 없습니다.";

    private final Name name;
    private final Map<ResultType, Integer> dealerResult;

    public DealerResult(Name name, Map<ResultType, Integer> dealerResult) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        validateDealerResult(dealerResult);

        this.name = name;
        this.dealerResult = dealerResult;
    }

    private void validateDealerResult(Map<ResultType, Integer> dealerResult) {
        Objects.requireNonNull(dealerResult, NULL_ERR_MSG);

        if (dealerResult.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERR_MSG);
        }
    }

    public String showDealerRecord() {
        return Arrays.stream(ResultType.values())
                .filter(dealerResult::containsKey)
                .map(type -> dealerResult.get(type) + type.getWord())
                .collect(Collectors.joining(DELIMITER));
    }

    public String name() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealerResult that = (DealerResult) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dealerResult, that.dealerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dealerResult);
    }
}

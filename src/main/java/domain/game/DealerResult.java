package domain.game;

import java.util.Map;
import java.util.stream.Collectors;

public class DealerResult {
    private static final int NONE = 0;
    private final Map<Result, Integer> dealerResult;

    public DealerResult(Map<Result, Integer> dealerResult) {
        this.dealerResult = dealerResult;
    }

    public String getInformation() {
        return dealerResult.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > NONE)
                .map(entry -> entry.getValue() + entry.getKey().getResult())
                .collect(Collectors.joining(" "));
    }
}

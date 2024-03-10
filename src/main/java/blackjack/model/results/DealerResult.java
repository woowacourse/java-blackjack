package blackjack.model.results;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DealerResult {
    private final Map<Result, Long> dealerResult;

    public DealerResult(PlayerResult playerResult) {
        this.dealerResult = calculateDealerResult(playerResult);
    }

    private Map<Result, Long> calculateDealerResult(PlayerResult playerResult) {
        return playerResult.getResult().values().stream()
                .collect(Collectors.groupingBy(
                        this::calculateDealerResult,
                        () -> new EnumMap<>(Result.class),
                        Collectors.counting()
                ));
    }

    private Result calculateDealerResult(Result playerResult) {
        if (playerResult == Result.WIN) {
            return Result.LOSE;
        }
        if (playerResult == Result.LOSE) {
            return Result.WIN;
        }
        return Result.PUSH;
    }

    public Map<Result, Long> getDealerResult() {
        return dealerResult;
    }
}

package domain.participant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int MAX_CARD_SUM = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public Map<Result, Integer> checkResult(List<Result> playersResult) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);

        for (Result result : Result.values()) {
            dealerResult.put(result.reverseResult(), countTargetResult(playersResult, result));
        }

        return dealerResult;
    }

    private int countTargetResult(List<Result> playersResult, Result targetResult) {
        return (int) playersResult.stream()
            .filter(result -> result == targetResult)
            .count();
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() <= MAX_CARD_SUM;
    }
}

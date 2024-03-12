package blackjack.model.participants;

import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Participant {
    private static final int STAND_THRESHOLD = 17;

    @Override
    public boolean canHit() {
        return cards.getScore() < STAND_THRESHOLD;
    }

    public DealerResult getResult(PlayerResult playerResult) {
        Map<Result, Long> result = playerResult.getResult().values().stream()
                .collect(Collectors.groupingBy(
                        this::convertToDealerResult,
                        () -> new EnumMap<>(Result.class),
                        Collectors.counting()
                ));

        return new DealerResult(result);
    }

    private Result convertToDealerResult(Result playerResult) {
        if (playerResult == Result.WIN_BY_BLACKJACK) {
            return Result.LOSE_BY_BLACKJACK;
        }
        if (playerResult == Result.WIN) {
            return Result.LOSE;
        }
        if (playerResult == Result.LOSE) {
            return Result.WIN;
        }
        return Result.PUSH;
    }
}

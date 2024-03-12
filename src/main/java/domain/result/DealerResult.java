package domain.result;

import domain.gamer.Player;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DealerResult {
    private final Map<Result, Integer> result;

    private DealerResult(final Map<Result, Integer> result) {
        this.result = result;
    }

    public static DealerResult createOppositeResult(PlayerResults otherResult) {
        Map<Player, Result> playerResults = otherResult.getResults();
        Map<Result, Integer> collect = playerResults.values().stream()
                .map(Result::getOppositeResult)
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        return new DealerResult(collect);
    }

    public int getWinCount() {
        return result.getOrDefault(Result.WIN, 0);
    }

    public int getLoseCount() {
        return result.getOrDefault(Result.LOSE, 0);
    }

    public int getTieCount() {
        return result.getOrDefault(Result.TIE, 0);
    }
}

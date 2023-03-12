package blackjack.domain.game;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Result {

    private final Map<GameResult, Integer> result;

    public Result() {
        this.result = new EnumMap<>(GameResult.class);
        initResult();
    }

    private Result(final Map result) {
        this.result = result;
    }

    private void initResult() {
        Arrays.stream(GameResult.values())
                .forEach((gameResult) -> result.put(gameResult, 0));
    }

    public Result updateResult(final GameResult gameResult) {
        final Map<GameResult, Integer> newResult = new EnumMap<>(result);
        newResult.put(gameResult, result.get(gameResult) + 1);

        return new Result(newResult);
    }

    public Map<GameResult, Integer> getResult() {
        return new EnumMap<>(result);
    }
}

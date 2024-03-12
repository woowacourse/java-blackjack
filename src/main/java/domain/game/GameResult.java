package domain.game;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN((current, opponent) -> current > opponent),
    LOSE((current, opponent) -> current < opponent),
    DRAW(Integer::equals);

    private final BiPredicate<Integer, Integer> condition;

    GameResult(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public GameResult reverse() {
        return switch (this) {
            case WIN -> LOSE;
            case LOSE -> WIN;
            case DRAW -> DRAW;
        };
    }

    public static GameResult compare(int current, int opponent) {
        return Arrays.stream(values())
                .filter(result -> result.condition.test(current, opponent))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("Result의 BiPredicate가 모든 경우의 수를 포함하지 않습니다."));
    }
}

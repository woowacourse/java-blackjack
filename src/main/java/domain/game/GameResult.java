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
                        new IllegalStateException("입력에 따른 결과가 존재하지 않습니다."));
    }
}

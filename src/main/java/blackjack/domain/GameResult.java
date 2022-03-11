package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN("승", (score1, score2) -> score1 > score2),
    DRAW("무", (score1, score2) -> score1 == score2),
    LOSE("패", (score1, score2) -> score1 < score2),
        ;

    private final String result;
    private final BiPredicate<Integer, Integer> condition;

    GameResult(final String result, final BiPredicate<Integer, Integer> condition) {
        this.result = result;
        this.condition = condition;
    }

    public static GameResult of(final int score1, final int score2) {
        return Arrays.stream(values())
            .filter(it -> it.condition.test(score1, score2))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 점수가 입력되었습니다."));
    }

    public GameResult reverse() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return this;
    }

    public String getResult() {
        return result;
    }
}

package blackjack.domain.result;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public enum JudgeResult {

    WIN((self, counter) -> self > counter),
    PUSH(Objects::equals),
    LOSE((self, counter) -> self < counter);

    private final BiPredicate<Integer, Integer> scoreComparer;

    JudgeResult(final BiPredicate<Integer, Integer> scoreComparer) {
        this.scoreComparer = scoreComparer;
    }

    public static JudgeResult match(final int selfScore, final int counterScore) {
        return Arrays.stream(values())
                .filter(result -> result.scoreComparer.test(selfScore, counterScore))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("판정 결과로 해당하는 값을 찾을 수 없습니다."));
    }

    public static JudgeResult counter(final JudgeResult judgeResult) {
        if (judgeResult == WIN) {
            return LOSE;
        }
        if (judgeResult == LOSE) {
            return WIN;
        }
        return PUSH;
    }
}

package domain.gamer;

import java.util.Arrays;
import java.util.function.Predicate;

public enum WinOrLose {
    WIN("승", intervalScore -> intervalScore > 0),
    DRAW("무", intervalScore -> intervalScore == 0),
    LOSE("패", intervalScore -> intervalScore < 0);

    private final String initial;
    private final Predicate<Integer> resultPredicate;

    WinOrLose(String initial, Predicate<Integer> resultPredicate) {
        this.initial = initial;
        this.resultPredicate = resultPredicate;
    }

    public static WinOrLose of(int intervalScore) {
        return Arrays.stream(WinOrLose.values())
                .filter(x -> x.resultPredicate.test(intervalScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
    }

    public String getInitial() {
        return initial;
    }
}

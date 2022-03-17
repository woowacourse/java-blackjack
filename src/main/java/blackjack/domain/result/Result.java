package blackjack.domain.result;

import java.util.Arrays;
import java.util.Comparator;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public static Result fromBoolean(final Boolean isWin) {
        if (isWin) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public Result toReverse() {
        return (Result) Arrays.stream(Result.values())
                .sorted(Comparator.reverseOrder())
                .toArray()[this.ordinal()];
    }

    public String get() {
        return value;
    }
}

package model;

import java.util.Arrays;

public enum Result {
    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0);

    String result;
    int resultValue;

    Result(String result, int resultValue) {
        this.result = result;
        this.resultValue = resultValue;
    }

    public static Result calculateResult(int compareValue) {
        return Arrays.stream(Result.values())
            .filter(result -> result.resultValue == compareValue)
            .findFirst()
            .get();
    }

    public static Result oppositeResult(Result result) {
        if (isWin(result)) {
            return LOSE;
        }
        if (isLose(result)) {
            return WIN;
        }
        return DRAW;
    }

    private static boolean isLose(final Result result) {
        return result == LOSE;
    }

    private static boolean isWin(final Result result) {
        return result == WIN;
    }

    @Override
    public String toString() {
        return result;
    }
}

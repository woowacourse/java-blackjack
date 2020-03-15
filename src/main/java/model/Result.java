package model;

import java.util.Arrays;

public enum Result {
    WIN("승", -1),
    LOSE("패", 1),
    DRAW("무", 0);

    String result;
    int resultValue;

    Result(String result, int resultValue) {
        this.result = result;
        this.resultValue = resultValue;
    }

    @Override
    public String toString() {
        return result;
    }

    public static Result calculateResult(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isBust()) {
            return DRAW;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (player.isBust()) {
            return LOSE;
        }
        int compareValue = User.compare(dealer, player);
        return Arrays.stream(Result.values())
                .filter(result -> result.isSameResult(compareValue))
                .findFirst()
                .orElseGet(()->DRAW);
    }

    private boolean isSameResult(int compareValue) {
        return this.resultValue == compareValue;
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


}

package model;

import exception.IllegalResultException;

import java.util.Arrays;

public enum Result {
    WIN("승", -1),
    LOSE("패", 1),
    DRAW("무", 0);

    private final String result;
    private final int resultValue;

    Result(String result, int resultValue) {
        this.result = result;
        this.resultValue = resultValue;
    }

    public static Result compete(Dealer dealer, Player player) {
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
                .orElseThrow(() -> new IllegalResultException("올바른 비교 값이 아닙니다."));
    }

    private boolean isSameResult(int compareValue) {
        return this.resultValue == compareValue;
    }

    @Override
    public String toString() {
        return result;
    }
}

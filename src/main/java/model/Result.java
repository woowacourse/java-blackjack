package model;

import exception.IllegalResultException;

import java.util.Arrays;

public enum Result {
    BLACKJACK(-2, 1.5),
    WIN(-1, 1.0),
    LOSE(1, -1.0),
    DRAW(0, 0.0);

    private final int compareValue;
    private final double ratio;

    Result(int compareValue, double ratio) {
        this.compareValue = compareValue;
        this.ratio = ratio;
    }

    public static Result compete(Dealer dealer, Player player) {
        if(dealer.isBlackJack() && player.isBlackJack()){
            return DRAW;
        }

        if(dealer.isBlackJack()){
            return LOSE;
        }

        if(player.isBlackJack()){
            return BLACKJACK;
        }

        if (dealer.isBust() && player.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        if (player.isBust()) {
            return LOSE;
        }
        int compareValue = dealer.compareTo(player);
        return Arrays.stream(Result.values())
                .filter(result -> result.isSameResult(compareValue))
                .findFirst()
                .orElseThrow(() -> new IllegalResultException("올바른 비교 값이 아닙니다."));
    }

    private boolean isSameResult(int compareValue) {
        return this.compareValue == compareValue;
    }
}

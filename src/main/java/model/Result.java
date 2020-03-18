package model;

import exception.IllegalResultException;

import java.util.Arrays;

public enum Result {
    BLACKJACK(-2, Constants.BLACK_JACK_RATIO),
    WIN(-1, Constants.WIN_RATIO),
    LOSE(1, Constants.LOSE_RATIO),
    DRAW(0, Constants.DRAW_RATIO);

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

    private static class Constants {
        public static final double BLACK_JACK_RATIO = 1.5;
        public static final double WIN_RATIO = 1.0;
        public static final double LOSE_RATIO = -1.0;
        public static final double DRAW_RATIO = 0.0;
    }
}

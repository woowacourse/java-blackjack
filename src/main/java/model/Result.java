package model;

import exception.IllegalResultException;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    BLACKJACK(Constants.BLACK_JACK_RATIO, ((dealer, player) ->
            !(dealer.isBlackJack()) && player.isBlackJack())
    ),
    WIN(Constants.WIN_RATIO, (dealer, player) -> {
        if (!player.isBust() && !player.isBlackJack()) {
            return dealer.isBust() || dealer.isLowerThan(player);
        }
        return false;
    }),
    LOSE(Constants.LOSE_RATIO, (dealer, player) -> {
        if (!player.isBlackJack()) {
            return player.isBust()
                    || (!player.isBust() && !dealer.isBust() && player.isLowerThan(dealer))
                    || (dealer.isBlackJack() && !player.isBlackJack());
        }
        return false;
    }),
    DRAW(Constants.DRAW_RATIO, (dealer, player) -> dealer.isBlackJack() && player.isBlackJack()
            || (!player.isBust() && !dealer.isBust() && player.isSameWith(dealer)));

    private final double ratio;
    private final BiFunction<Dealer, Player, Boolean> function;

    Result(double ratio, BiFunction<Dealer, Player, Boolean> function) {
        this.ratio = ratio;
        this.function = function;
    }

    public Profit calculateProfit(Player player) {
        return new Profit(player.getMultiplyBet(ratio));
    }

    public static Result compete(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(result -> result.function.apply(dealer, player))
                .findAny()
                .orElseThrow(() -> new IllegalResultException("올바른 비교 값이 아닙니다."));
    }

    private static class Constants {
        public static final double BLACK_JACK_RATIO = 1.5;
        public static final double WIN_RATIO = 1.0;
        public static final double LOSE_RATIO = -1.0;
        public static final double DRAW_RATIO = 0.0;
    }
}

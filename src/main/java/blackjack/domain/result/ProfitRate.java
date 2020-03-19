package blackjack.domain.result;

import blackjack.domain.playing.user.User;
import blackjack.domain.result.Exception.PofitRateException;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum ProfitRate {
    PLAYER_IS_BLACKJACK(((player, dealer) -> player.isBlackjack() && dealer.isNotBlackjack()), 1.5),
    DEALER_IS_BLACKJACK(((player, dealer) -> player.isBlackjack() && dealer.isBlackjack()), 1.0),
    PLAYER_IS_BUST((player, dealer) -> player.isBust(), -1.0),
    DEALER_IS_BUST((player, dealer) -> player.isNotBust() && dealer.isBust(), 1.0),
    PLAYER_SCORE_IS_GREATER(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isOverScore(dealer)), 1.0),
    PLAYER_SCORE_IS_SAME(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isSameScore(dealer)), 0.0),
    PLAYER_SCORE_IS_LOWER(((player, dealer) -> player.isNotBust() && dealer.isNotBust() && player.isUnderScore(dealer)), -1.0);

    private BiFunction<User, User, Boolean> condition;
    private double profit;

    ProfitRate(BiFunction<User, User, Boolean> condition, double profit) {
        this.condition = condition;
        this.profit = profit;
    }

    public static double of(User player, User dealer) {
        return Arrays.stream(values())
                .filter(rule -> rule.apply(player, dealer))
                .findFirst()
                .orElseThrow(PofitRateException::new)
                .profit;
    }

    private Boolean apply(User player, User dealer) {
        return condition.apply(player, dealer);
    }
}

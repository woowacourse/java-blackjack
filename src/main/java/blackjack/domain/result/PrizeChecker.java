package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.GamePlayer;

import java.util.function.BiPredicate;


public enum PrizeChecker {
    BLACKJACK((gamePlayer, dealer) -> gamePlayer.isBlackjack() && !dealer.isBlackjack(), 1.5),
    WIN((gamePlayer, dealer) -> !gamePlayer.isBust() && dealer.isBust() || (
            gamePlayer.isStand() && compare(gamePlayer, dealer) > 0), 1),
    DRAW(((gamePlayer, dealer) -> (gamePlayer.isBlackjack() && dealer.isBlackjack()) || (
            gamePlayer.isStand() && compare(gamePlayer, dealer) == 0)), 0),

    LOSE((gamePlayer, dealer) -> gamePlayer.isBust() || (
            gamePlayer.isStand() && compare(gamePlayer, dealer) < 0), -1);
    private final BiPredicate<GamePlayer, Dealer> predicate;
    private final double profitRate;

    PrizeChecker(final BiPredicate<GamePlayer, Dealer> predicate, final double profitRate) {
        this.predicate = predicate;
        this.profitRate = profitRate;
    }

    public static final PrizeMoney check(final Dealer dealer, final GamePlayer gamePlayer) {
        for (final var checker : PrizeChecker.values()) {
            if (checker.predicate.test(gamePlayer, dealer)) {
                return new PrizeMoney(gamePlayer.getBettingMoney() * checker.profitRate);
            }
        }
        throw new IllegalStateException("조건에 맞지 않는 계산입니다");
    }

    private static int compare(final GamePlayer gamePlayer, final Dealer dealer) {
        return Integer.compare(gamePlayer.calculateScore(), dealer.calculateScore());
    }
}

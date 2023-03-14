package domain.type;

import domain.model.Dealer;
import domain.model.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    BLACKJACK_VICTORY(1.5D, Result::isBlackJackWin),
    VICTORY(1D, Result::isWin),
    DRAW(0D, Result::isDraw),
    DEFEAT(-1D, Result::isDefeat),
    ;

    private final double rate;
    private final BiPredicate<Player, Dealer> condition;

    Result(final double rate, final BiPredicate<Player, Dealer> condition) {
        this.rate = rate;
        this.condition = condition;
    }

    public static Result findResult(final Player player, final Dealer dealer) {
        return Arrays.stream(Result.values())
            .filter(result -> result.condition.test(player, dealer))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isBlackJackWin(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isNotBlackJack();
    }

    private static boolean isWin(final Player player, final Dealer dealer) {
        return player.isStand() && winnable(player, dealer);
    }

    private static boolean isDraw(final Player player, final Dealer dealer) {
        return player.getScore().equals(dealer.getScore());
    }

    private static boolean isDefeat(final Player player, final Dealer dealer) {
        return player.isBust() || dealer.getScore().moreThan(player.getScore());
    }

    private static boolean winnable(final Player player, final Dealer dealer) {
        return bothBlackJack(player, dealer)
            || player.getScore().moreThan(dealer.getScore())
            || dealer.isBust();
    }

    private static boolean bothBlackJack(final Player player, final Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    public double getRate() {
        return rate;
    }
}

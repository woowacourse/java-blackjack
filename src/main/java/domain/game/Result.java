package domain.game;

import domain.player.Dealer;
import domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    DEALER_WIN(Result::isDealerWin, -1),
    PLAYER_WIN(Result::isPlayerWin, 1),
    PUSH(Result::isPush, 0),
    PLAYER_BLACK_JACK(Result::isPlayerBlackJack, 1.5);


    private final BiPredicate<Dealer, Player> judgeResult;
    private final double profitRate;

    Result(final BiPredicate<Dealer, Player> judgeResult, final double profitRate) {
        this.judgeResult = judgeResult;
        this.profitRate = profitRate;
    }

    public static Result of(final Dealer dealer, final Player player) {
        return Arrays.stream(values())
                .filter(value -> value.judgeResult.test(dealer, player))
                .findAny()
                .orElseThrow();

    }

    private static boolean isDealerWin(final Dealer dealer, final Player player) {
        return player.isBust()
                || !dealer.isBust() && dealer.getScore().compareTo(player.getScore()) > 0;
    }

    private static boolean isPlayerWin(final Dealer dealer, final Player player) {
        return (dealer.isBust()
                || !player.isBust() && dealer.getScore().compareTo(player.getScore()) < 0) && !player.isBlackJack();
    }

    private static boolean isPush(final Dealer dealer, final Player player) {
        return dealer.getScore().compareTo(player.getScore()) == 0;
    }

    private static boolean isPlayerBlackJack(final Dealer dealer, final Player player) {
        return player.isBlackJack();
    }

    public double getProfitRate() {
        return profitRate;
    }
}

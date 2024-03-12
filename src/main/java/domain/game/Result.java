package domain.game;

import domain.player.Dealer;
import domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    DEALER_WIN(Result::isDealerWin),
    PLAYER_WIN(Result::isPlayerWin),
    PUSH(Result::isPush);

    private final BiPredicate<Dealer, Player> judgeResult;

    Result(final BiPredicate<Dealer, Player> judgeResult) {
        this.judgeResult = judgeResult;
    }

    public static Result of(Dealer dealer, Player player) {
        return Arrays.stream(values())
                .filter(value -> value.judgeResult.test(dealer, player))
                .findAny()
                .orElseThrow();
    }

    private static boolean isDealerWin(final Dealer dealer, final Player player) {
        return player.isBust()
                || !dealer.isBust() && dealer.getTotalScore().compareTo(player.getTotalScore()) > 0;
    }

    private static boolean isPlayerWin(final Dealer dealer, final Player player) {
        return dealer.isBust()
                || !player.isBust() && dealer.getTotalScore().compareTo(player.getTotalScore()) < 0;
    }

    private static boolean isPush(final Dealer dealer, final Player player) {
        return dealer.getTotalScore().compareTo(player.getTotalScore()) == 0;
    }
}

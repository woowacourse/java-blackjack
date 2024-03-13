package domain.game;

import domain.player.Dealer;
import domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    DEALER_WIN(Result::isDealerWin),
    PLAYER_WIN(Result::isPlayerWin),
    PUSH(Result::isPush),
    PLAYER_BLACK_JACK(Result::isPlayerBlackJack);


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

    public boolean isDealerWin() {
        return this == DEALER_WIN;
    }

    public boolean isPlayerWin() {
        return this == PLAYER_WIN;
    }

    public boolean isPush() {
        return this == PUSH;
    }

    public boolean isPlayerBlackJack() {
        return this == PLAYER_BLACK_JACK;
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
}

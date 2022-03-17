package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public enum PlayerResult {

    BLACKJACK_WIN(1.5, PlayerResult::isBlackjackWin),
    WIN(1, PlayerResult::isWin),
    DRAW(0, PlayerResult::isDraw),
    LOSS(-1, PlayerResult::isLoss);

    private final double profitRate;
    private final BiPredicate<Player, Dealer> predicate;

    PlayerResult(final double profitRate, final BiPredicate<Player, Dealer> predicate) {
        this.profitRate = profitRate;
        this.predicate = predicate;
    }

    public static PlayerResult of(final Player player, final Dealer dealer) {
        return Arrays.stream(values())
                .filter(result -> result.predicate.test(player, dealer))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 결과입니다."));
    }

    private static boolean isBlackjackWin(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isLoss(Player player, Dealer dealer) {
        return (player.isBust()) ||
                (!player.isBlackjack() && dealer.isBlackjack()) ||
                (dealer.hasHigherScoreThan(player));
    }

    private static boolean isDraw(Player player, Dealer dealer) {
        return (dealer.isBlackjack() && player.isBlackjack()) ||
                (player.hasSameScoreWith(dealer) && !player.isBust());
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return (dealer.isBust() && !player.isBust()) ||
                (player.hasHigherScoreThan(dealer) && !player.isBust());
    }

    public double getProfitRate() {
        return profitRate;
    }
}

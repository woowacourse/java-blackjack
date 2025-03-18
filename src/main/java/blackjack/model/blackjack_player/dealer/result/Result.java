package blackjack.model.blackjack_player.dealer.result;

import blackjack.model.blackjack_player.dealer.Dealer;
import blackjack.model.blackjack_player.player.Player;

public enum Result {

    DEALER_WIN,
    DEALER_LOSE,
    DRAW;

    public static Result calculate(final Dealer dealer, final Player player) {
        if (isDraw(dealer, player)) {
            return DRAW;
        }
        if (isDealerWin(dealer, player)) {
            return DEALER_WIN;
        }
        return DEALER_LOSE;
    }

    private static boolean isDraw(final Dealer dealer, final Player player) {
        if (isDealerOrPlayerBust(dealer, player)) {
            return false;
        }
        if (dealer.isBlackjack()) {
            return player.isBlackjack();
        }
        return dealer.getOptimalPoint() == player.getOptimalPoint();
    }

    private static boolean isDealerWin(final Dealer dealer, final Player player) {
        if (isPlayerOnlyBust(dealer, player) || isDealerOnlyBlackjack(dealer, player)) {
            return true;
        }
        if (dealer.isBust() || isPlayerOnlyBlackjack(dealer, player)) {
            return false;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return false;
        }
        return dealer.getOptimalPoint() > player.getOptimalPoint();
    }

    private static boolean isDealerOrPlayerBust(final Dealer dealer, final Player player) {
        return dealer.isBust() || player.isBust();
    }

    private static boolean isDealerOnlyBlackjack(final Dealer dealer, final Player player) {
        return dealer.isBlackjack() && !player.isBlackjack();
    }

    private static boolean isPlayerOnlyBlackjack(final Dealer dealer, final Player player) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isPlayerOnlyBust(final Dealer dealer, final Player player) {
        return player.isBust() && !dealer.isBust();
    }
}

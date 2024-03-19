package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

class Judge {

    public static PlayerOutcome calculatePlayerOutcome(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return calculateBlackjackCase(dealer, player);
        }
        if (dealer.isBust() || player.isBust()) {
            return calculateBustCase(player);
        }
        return calculateNormalCase(dealer, player);
    }

    private static PlayerOutcome calculateBlackjackCase(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return PlayerOutcome.PUSH;
        }
        if (dealer.isBlackjack()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.BLACKJACK_WIN;
    }

    private static PlayerOutcome calculateBustCase(final Player player) {
        if (player.isBust()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.NORMAL_WIN;
    }

    private static PlayerOutcome calculateNormalCase(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.calculateScore();
        final int playerScore = player.calculateScore();

        if (dealerScore < playerScore) {
            return PlayerOutcome.NORMAL_WIN;
        }
        if (dealerScore > playerScore) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.PUSH;
    }
}

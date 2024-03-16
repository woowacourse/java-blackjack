package blackjack.domain.result;

import blackjack.domain.gamers.Dealer;
import blackjack.domain.gamers.Player;

public class Judge {

    private final Dealer dealer;

    public Judge(final Dealer dealer) {
        this.dealer = dealer;
    }

    public PlayerOutcome calculatePlayerOutcome(final Player player) {
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return calculateBlackjackCase(player);
        }
        if (dealer.isBust() || player.isBust()) {
            return calculateBustCase(player);
        }
        return calculateNormalCase(player);
    }

    private PlayerOutcome calculateBlackjackCase(final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return PlayerOutcome.PUSH;
        }
        if (dealer.isBlackjack()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.BLACKJACK_WIN;
    }

    private PlayerOutcome calculateBustCase(final Player player) {
        if (player.isBust()) {
            return PlayerOutcome.LOSE;
        }
        return PlayerOutcome.NORMAL_WIN;
    }

    private PlayerOutcome calculateNormalCase(final Player player) {
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

package blackjack.domain.result;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;

public final class PrideCalculator {
    private final int playerMoney;

    public PrideCalculator(Player player, Dealer dealer) {
        this.playerMoney = compute(player, dealer);
    }

    private int compute(Player player, Dealer dealer) {
        if (player.isCardsThatSize(2) && player.isBlackjack() && !dealer.isBlackjack()) {
            return player.getBetting()
                    .getMultiple(1.5)
                    .hashCode();

        }
        if (dealer.isBust() || !player.isBust() && player.hasMorePoint(dealer)) {
            return player.getBetting()
                    .getMultiple(1.0)
                    .hashCode();
        }
        if (player.isBust() || (!player.hasMorePoint(dealer) && !player.isDraw(dealer))) {
            return player.getBetting()
                    .getMultiple(-1.0)
                    .hashCode();
        }
        return 0;
    }

    public int get() {
        return playerMoney;
    }
}

package blackjack.domain.result;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;

public final class PayoutCalculator {
    private final int playerMoney;

    public PayoutCalculator(Player player, Dealer dealer) {
        this.playerMoney = compute(player, dealer);
    }

    public static int compute(Player player, Dealer dealer) {
        if (player.hasCardSizeOf(2) && player.isMaxPoint() && !dealer.isMaxPoint()) {
            return player.getMultipliedMoney(1.5);
        }
        if (dealer.isBust() || !player.isBust() && player.hasMorePoint(dealer)) {
            return player.getMultipliedMoney(1.0);
        }
        if (player.isBust() || (!player.hasMorePoint(dealer) && !player.isDrawState(dealer))) {
            return player.getMultipliedMoney(-1.0);
        }
        return 0;
    }

    public int get() {
        return playerMoney;
    }
}

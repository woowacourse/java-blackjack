package blackjack.domain.result;

import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;

public final class PayoutCalculator {
    public static int compute(Player player, Dealer dealer) {
        return player.getProfit(dealer);
    }
}

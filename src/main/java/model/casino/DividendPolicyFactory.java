package model.casino;

import model.money.DividendPolicy;
import model.participant.Dealer;
import model.participant.Player;

public class DividendPolicyFactory {
    private final Dealer dealer;

    public DividendPolicyFactory(Dealer dealer) {
        this.dealer = dealer;
    }

    public DividendPolicy findPolicyInProceed(Player player) {
        if (player.isInitBlackjack()) {
            return DividendPolicy.INIT_BLACKJACK;
        }
        if (player.isBustHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        return DividendPolicy.UNCERTAIN;
    }

    public DividendPolicy findPolicyAfterProceed(Player player) {
        if (!dealer.isBustHand() && !player.isBustHand()) {
            return getDividendPolicyHasNoBust(player);
        }
        if (dealer.isBustHand() && player.isBustHand()) {
            return DividendPolicy.NORMAL_DRAW;
        }
        if (dealer.isBustHand()) {
            return DividendPolicy.NORMAL_WIN;
        }
        throw new IllegalStateException();
    }

    private DividendPolicy getDividendPolicyHasNoBust(Player player) {
        if (dealer.getHand() > player.getHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        if (dealer.getHand() == player.getHand()) {
            return DividendPolicy.NORMAL_DRAW;
        }
        return DividendPolicy.NORMAL_WIN;
    }


}

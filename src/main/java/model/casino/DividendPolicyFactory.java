package model.casino;

import model.money.DividendPolicy;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;

public class DividendPolicyFactory {

    public static DividendPolicy findPolicy(Participant dealer, Participant player) {
        DividendPolicy policyInProceed = findPolicyInProceed(player);
        if (policyInProceed != DividendPolicy.UNCERTAIN) {
            return policyInProceed;
        }
        return findPolicyAfterProceed((Dealer)dealer, (Player)player);
    }

    private static DividendPolicy findPolicyInProceed(Participant participant) {
        Player player = (Player) participant;
        if (player.isInitBlackjack()) {
            return DividendPolicy.INIT_BLACKJACK;
        }
        if (player.isBustHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        return DividendPolicy.UNCERTAIN;
    }

    private static DividendPolicy findPolicyAfterProceed(Dealer dealer, Player player) {
        if (!dealer.isBustHand() && !player.isBustHand()) {
            return getDividendPolicyHasNoBust(dealer, player);
        }
        if (dealer.isBustHand() && player.isBustHand()) {
            return DividendPolicy.NORMAL_DRAW;
        }
        if (dealer.isBustHand()) {
            return DividendPolicy.NORMAL_WIN;
        }
        throw new IllegalStateException();
    }

    private static DividendPolicy getDividendPolicyHasNoBust(Dealer dealer, Player player) {
        if (dealer.getHand() > player.getHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        if (dealer.getHand() == player.getHand()) {
            return DividendPolicy.NORMAL_DRAW;
        }
        return DividendPolicy.NORMAL_WIN;
    }
}

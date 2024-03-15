package model.casino;

import model.money.DividendPolicy;
import model.participant.Participant;

public class DividendPolicyFactory {

    public static DividendPolicy findPolicy(Participant dealer, Participant player) {
        DividendPolicy policyInProceed = findPolicyInProceed(player);
        if (policyInProceed != DividendPolicy.UNCERTAIN) {
            return policyInProceed;
        }
        return findPolicyAfterProceed(dealer, player);
    }

    private static DividendPolicy findPolicyInProceed(Participant participant) {
        if (participant.isInitBlackjack()) {
            return DividendPolicy.INIT_BLACKJACK;
        }
        if (participant.isBustHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        return DividendPolicy.UNCERTAIN;
    }

    private static DividendPolicy findPolicyAfterProceed(Participant dealer, Participant player) {
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

    private static DividendPolicy getDividendPolicyHasNoBust(Participant dealer, Participant player) {
        if (dealer.getHand() > player.getHand()) {
            return DividendPolicy.NORMAL_LOSE;
        }
        if (dealer.getHand() == player.getHand()) {
            return DividendPolicy.NORMAL_DRAW;
        }
        return DividendPolicy.NORMAL_WIN;
    }
}

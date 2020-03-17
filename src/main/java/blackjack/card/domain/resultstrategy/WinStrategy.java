package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class WinStrategy extends GameResultStrategy {

    private static final int WIN_VALUE = 1;

    @Override
    protected boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return isOnlyDealerBurst(gamblerCardBundle, dealerCardBundle) || isOnlyGamblerBlackjack(gamblerCardBundle, dealerCardBundle);
    }

    private boolean isOnlyGamblerBlackjack(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return gamblerCardBundle.isBlackjack() && !dealerCardBundle.isBlackjack();
    }

    private boolean isOnlyDealerBurst(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return dealerCardBundle.isBurst() && !gamblerCardBundle.isBurst();
    }

    @Override
    protected boolean isMatch(int compare) {
        return WIN_VALUE == compare;
    }
}

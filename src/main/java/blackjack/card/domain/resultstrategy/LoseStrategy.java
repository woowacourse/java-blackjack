package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class LoseStrategy extends GameResultStrategy {

    private static final int LOSE_VALUE = -1;

    @Override
    protected boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return isOnlyGamblerBurst(gamblerCardBundle, dealerCardBundle) || isOnlyDealerBlackjack(gamblerCardBundle, dealerCardBundle);
    }

    private boolean isOnlyDealerBlackjack(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return dealerCardBundle.isBlackjack() && !gamblerCardBundle.isBlackjack();
    }

    private boolean isOnlyGamblerBurst(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return gamblerCardBundle.isBurst() && !dealerCardBundle.isBurst();
    }

    @Override
    protected boolean isMatch(int compare) {
        return LOSE_VALUE == compare;
    }
}

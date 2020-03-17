package blackjack.card.domain.resultstrategy;

import blackjack.card.domain.CardBundle;

public class DrawStrategy extends GameResultStrategy {

    private static final int DRAW_VALUE = 0;

    @Override
    protected boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return allMatchBurst(gamblerCardBundle, dealerCardBundle) || allMatchBlackjack(gamblerCardBundle, dealerCardBundle);
    }

    private boolean allMatchBlackjack(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return gamblerCardBundle.isBlackjack() && dealerCardBundle.isBlackjack();
    }

    private boolean allMatchBurst(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return gamblerCardBundle.isBurst() && dealerCardBundle.isBurst();
    }

    @Override
    protected boolean isMatch(int compare) {
        return DRAW_VALUE == compare;
    }
}

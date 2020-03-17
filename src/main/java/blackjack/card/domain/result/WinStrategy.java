package blackjack.card.domain.result;

import blackjack.card.domain.CardBundle;

public class WinStrategy extends GameResultStrategy {

    private static final int WIN_VALUE = 1;

    @Override
    protected boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return dealerCardBundle.isBurst() && !gamblerCardBundle.isBurst() || gamblerCardBundle.isBlackjack() && !dealerCardBundle.isBlackjack();
    }

    @Override
    protected boolean isMatch(int compare) {
        return WIN_VALUE == compare;
    }
}

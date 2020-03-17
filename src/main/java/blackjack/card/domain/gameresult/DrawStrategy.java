package blackjack.card.domain.gameresult;

import blackjack.card.domain.CardBundle;

public class DrawStrategy extends GameResultStrategy {

    private static final int DRAW_VALUE = 0;

    @Override
    protected boolean enough(CardBundle gamblerCardBundle, CardBundle dealerCardBundle) {
        return gamblerCardBundle.isBlackjack() && dealerCardBundle.isBlackjack();
    }

    @Override
    protected boolean isMatch(int compare) {
        return DRAW_VALUE == compare;
    }
}

package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Stay extends Finished {

    private static final int WIN_RATE = 1;
    private static final int LOST_RATE = -1;
    private static final int DRAW_RATE = 0;

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBust()) {
            return WIN_RATE;
        }
        if (dealer.isBlackjack() || isLesserThanDealerScore(dealer)) {
            return LOST_RATE;
        }
        return DRAW_RATE;
    }

    private boolean isLesserThanDealerScore(Dealer dealer) {
        return cards().calculateScore() < dealer.calculateScore();
    }
}

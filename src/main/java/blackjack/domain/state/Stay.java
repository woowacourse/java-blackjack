package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBust()) {
            return 1;
        }
        if (dealer.isBlackjack() || isLesserThanDealerScore(dealer)) {
            return -1;
        }
        return 0;
    }

    private boolean isLesserThanDealerScore(Dealer dealer) {
        return cards().calculateScore() < dealer.calculateScore();
    }
}

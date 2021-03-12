package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if (dealer.isBust() || score().toInt() > dealer.scoreToInt()) {
            return 1;
        }

        if (score().toInt() == dealer.scoreToInt()) {
            return 0;
        }

        return -1;
    }
}

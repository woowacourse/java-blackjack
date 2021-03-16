package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public class Blackjack extends Finished {
    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double profitRate(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}

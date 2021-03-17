package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;

public class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}

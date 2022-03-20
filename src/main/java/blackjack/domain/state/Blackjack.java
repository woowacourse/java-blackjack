package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Dealer dealer) {
        if (dealer.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}

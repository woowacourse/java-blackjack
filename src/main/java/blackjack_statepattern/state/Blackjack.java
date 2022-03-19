package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Cards cards) {
        if (cards.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}

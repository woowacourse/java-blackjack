package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(Cards cards) {
        return -1;
    }
}

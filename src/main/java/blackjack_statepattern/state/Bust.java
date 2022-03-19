package blackjack_statepattern.state;

import blackjack_statepattern.Cards;

public final class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return -1;
    }
}

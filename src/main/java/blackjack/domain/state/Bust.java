package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State state) {
        return -1;
    }
}

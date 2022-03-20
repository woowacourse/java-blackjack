package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State state) {
        return LOSE_RATE;
    }
}

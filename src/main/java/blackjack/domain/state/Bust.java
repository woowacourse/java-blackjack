package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Bust extends Finished {

    public static final int BUST_RATE = -1;

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State state) {
        return BUST_RATE;
    }
}

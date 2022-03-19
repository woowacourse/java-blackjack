package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {

    protected Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State state) {
        return -1;
    }
}

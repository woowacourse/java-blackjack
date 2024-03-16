package domain.card.state;

import domain.card.Cards;

public class Bust extends Finished {
    protected Bust(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return -1.0;
    }
}

package state.finished;

import card.Cards;

public class Stand extends Finished {

    public Stand(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 2;
    }
}

package domain.card.state;

import domain.card.Cards;

public class Stay extends Finished {
    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return 1.0;
    }
}

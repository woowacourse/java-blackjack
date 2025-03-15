package domain.state;

import domain.card.Cards;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return 1;
    }
}

package domain.state;

import domain.card.Cards;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    double earningRate() {
        return 1.5;
    }
}

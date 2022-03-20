package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return 1.5;
    }
}

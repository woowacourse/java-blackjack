package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {

    Bust(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate() {
        return -1;
    }
}

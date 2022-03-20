package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return -1.0;
    }
}

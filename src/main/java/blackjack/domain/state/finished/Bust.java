package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return -1;
    }

}

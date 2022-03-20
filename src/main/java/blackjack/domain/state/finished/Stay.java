package blackjack.domain.state.finished;

import blackjack.domain.cards.Cards;

public class Stay extends Finished {

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.0;
    }
}

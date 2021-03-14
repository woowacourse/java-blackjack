package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}

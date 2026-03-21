package domain.rule;

import domain.card.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State dealerState) {
        return -1.0;
    }

    @Override
    public boolean isBust() {
        return true;
    }
}

package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {

    public static final double EARNING_RATE = 1.5;

    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return EARNING_RATE;
    }
}

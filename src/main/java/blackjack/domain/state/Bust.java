package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {

    public static final int EARNING_RATE = -1;

    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return EARNING_RATE;
    }
}

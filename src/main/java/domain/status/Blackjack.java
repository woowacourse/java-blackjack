package domain.status;

import domain.participant.HandCards;

public final class Blackjack extends Finished {
    public Blackjack(final HandCards cards) {
        super(cards);
    }

    @Override
    public double earningsRate(Status dealerStatus) {
        if (dealerStatus instanceof Blackjack) {
            return 0;
        }
        return 1.5;
    }
}

package domain.state;

import domain.card.Hand;

public final class Blackjack extends Finished {
    Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfit() {
        return 1.5;
    }
}

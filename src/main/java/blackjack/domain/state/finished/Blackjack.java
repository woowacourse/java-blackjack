package blackjack.domain.state.finished;

import blackjack.domain.cards.Hand;

public class Blackjack extends Finished {

    private static final double PROFIT = 1.5;

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double getEarningRate() {
        return PROFIT;
    }
}

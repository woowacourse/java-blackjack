package blackjack.domain.state.finished;

import blackjack.domain.cards.Hand;

public class Bust extends Finished {

    private static final double PROFIT = 1.0;

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double getEarningRate() {
        return PROFIT;
    }
}

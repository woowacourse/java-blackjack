package domain.state;

import domain.card.Hand;

public final class Blackjack extends Finished {

    public static final double BLACKJACK_PROFIT_RATIO = 1.5;

    Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRatio() {
        return BLACKJACK_PROFIT_RATIO;
    }
}

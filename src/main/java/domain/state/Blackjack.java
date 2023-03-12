package domain.state;

import domain.user.Hand;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return 1.5;
    }
}

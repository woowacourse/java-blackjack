package domain.state;

import domain.member.Hand;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}

package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class Blackjack extends Finished{

    public Blackjack(Hand hand) {
        super(hand);
    }

    public double earningRate() {
        return 1.5d;
    }
}

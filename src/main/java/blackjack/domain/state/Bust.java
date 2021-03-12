package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return -1;
    }
}

package domain.state;

import domain.member.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return -1.0;
    }
}

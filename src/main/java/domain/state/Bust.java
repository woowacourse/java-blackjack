package domain.state;

import domain.user.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    double profitRate() {
        return 0;
    }
}

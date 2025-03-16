package domain.state.started.finished;

import domain.card.Hand;

public class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate() {
        return 1;
    }
}

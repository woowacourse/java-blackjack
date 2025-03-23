package blackjack.state.started.finished;

import blackjack.card.Hand;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate() {
        return 1;
    }
}

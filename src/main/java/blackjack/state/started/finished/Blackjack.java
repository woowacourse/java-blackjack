package blackjack.state.started.finished;

import blackjack.card.Hand;

public class Blackjack extends Finished {
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate() {
        return 1.5;
    }
}

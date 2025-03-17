package domain.state.type;

import domain.gamer.Hand;
import domain.state.Finished;

public class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }
}

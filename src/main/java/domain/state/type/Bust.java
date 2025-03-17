package domain.state.type;

import domain.gamer.Hand;
import domain.state.Finished;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }
}

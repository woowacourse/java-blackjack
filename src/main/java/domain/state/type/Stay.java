package domain.state.type;

import domain.gamer.Hand;
import domain.state.Finished;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }
}

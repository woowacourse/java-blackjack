package domain.participant.state;

import domain.participant.state.hand.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }
}

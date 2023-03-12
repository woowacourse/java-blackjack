package domain.state;

import domain.card.Hand;

public final class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }
}

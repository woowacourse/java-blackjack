package blackjack.blackjack.state.finished;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.StateType;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand, StateType.STAY);
    }
}

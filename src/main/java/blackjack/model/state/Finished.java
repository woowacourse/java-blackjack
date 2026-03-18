package blackjack.model.state;

import blackjack.model.card.Hand;

public abstract class Finished implements HandState{

    public void stay(Hand hand) {
        hand.setState(this);
    }
}

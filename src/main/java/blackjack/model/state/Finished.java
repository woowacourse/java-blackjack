package blackjack.model.state;

import blackjack.model.card.Hand;

public abstract class Finished implements HandState{

    public HandState stay(Hand hand) {
        return this;
    }
}

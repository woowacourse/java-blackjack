package blackjack.domain.state;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Score;

public abstract class State {
    final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    abstract State draw(Deck deck);

    abstract State stand();

    abstract boolean isFinished();

    Score calculateHand() {
        return hand.calculate();
    }

    Hand getHand() {
        return hand;
    }
}

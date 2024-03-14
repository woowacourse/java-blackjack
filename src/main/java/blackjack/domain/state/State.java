package blackjack.domain.state;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Score;

public abstract class State {
    final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Deck deck);

    public abstract State stand();

    public abstract boolean isFinished();

    public Score calculateHand() {
        return hand.sumScores();
    }

    public Hand getHand() {
        return hand;
    }
}

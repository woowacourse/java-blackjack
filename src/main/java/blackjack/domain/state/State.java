package blackjack.domain.state;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public abstract class State {
    private final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Deck deck);

    public abstract State stand();

    public abstract boolean isFinished();

    public Score calculateHand() {
        return hand.sumScores();
    }

    public abstract double getProfitRate(Hand other);

    public Hand getHand() {
        return hand;
    }
}

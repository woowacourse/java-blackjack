package domain.state;

import domain.card.Card;

public abstract class Finished implements State {

    protected final Hand hand;

    public Finished(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State drawCard(Card card) {
        throw new IllegalArgumentException();
    }

    @Override
    public Score calculateScore() {
        return hand.calculateScore();
    }
}

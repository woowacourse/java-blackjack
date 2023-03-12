package domain.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class State {
    private final Hand hand;

    protected State(Hand hand) {
        this.hand = hand;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public final List<Card> getCards() {
        return hand.getCards();
    }

    protected final Hand getHand() {
        return hand;
    }
}

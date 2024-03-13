package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public abstract class Participant {

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveInitialCards(final List<Card> cards) {
        hand.add(cards);
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    public int notifyScore() {
        return hand.calculateScore();
    }

    public List<Card> openCards() {
        return hand.getCards();
    }

    public abstract boolean canHit();

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean hasManyCardsThan(Participant other) {
        return hand.hasManyThan(other.hand);
    }

    public boolean hasSameCardsSizeThan(Participant other) {
        return hand.hasSameSizeWith(other.hand);
    }
}

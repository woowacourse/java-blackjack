package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public abstract class Participant {

    protected Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveInitialCards(final List<Card> cards) {
        for (Card card : cards) {
            hand.add(card);
        }
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

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean hasManyCardsThan(Participant other) {
        return hand.hasManyThan(other.hand);
    }

    public boolean hasSameCardsSizeThan(Participant other) {
        return hand.hasSameSizeWith(other.hand);
    }
}

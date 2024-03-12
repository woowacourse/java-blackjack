package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public class Participant {
    private static final int HITTABLE_THRESHOLD = 21;

    protected final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
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

    public boolean canHit() {
        return hand.calculateScore() < HITTABLE_THRESHOLD;
    }

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

package domain.gamer;

import domain.card.Card;
import java.util.List;

public abstract class Gamer {
    private final Name name;
    private final Hand hand;

    public Gamer(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public abstract boolean shouldStay();

    public void hit(final Card card) {
        hand.add(card);
    }

    public void receive(final List<Card> cards) {
        for (final Card card : cards) {
            hand.add(card);
        }
    }

    public int calculateTotalScore() {
        return hand.sum();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public Name getName() {
        return name;
    }
}

package blackjack.model;

import java.util.List;

public abstract class Participant {

    protected final Hand hand = new Hand();

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}

package blackjack.model;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public Participant(Hand hand) {
        this.hand = hand;
    }

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

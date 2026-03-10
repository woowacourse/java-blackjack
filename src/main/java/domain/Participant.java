package domain;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateTotalScore();
    }
}
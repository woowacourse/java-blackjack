package domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand;

    Participant() {
        this.hand = new Hand();
    }

    public void receiveCard(final Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public abstract String name();
}

package domain;

import java.util.List;

abstract class Participant {

    protected final Hand hand;

    Participant() {
        this.hand = new Hand();
    }

    abstract boolean isDrawable();

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public int score() {
        return hand.calculateScore();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    abstract String name();
}

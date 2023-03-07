package domain;

import java.util.List;

public abstract class Participant {

    private final Hand hand;

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

    public List<Card> hand() {
        return hand.getCards();
    }

    public abstract String name();
}

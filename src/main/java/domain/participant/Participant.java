package domain.participant;

import domain.card.Card;

import java.util.List;

public abstract class Participant {

    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void receive(Card card) {
        hand.receive(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}

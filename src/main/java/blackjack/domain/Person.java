package blackjack.domain;

import java.util.List;

abstract class Person {
    protected Hand hand;

    Person() {
        this.hand = new Hand();
    }

    abstract boolean isHitPossible();

    void addCard(Card card) {
        hand.add(card);
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }
}

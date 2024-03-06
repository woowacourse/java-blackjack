package domain;

import java.util.List;

abstract class Gamer {
    Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    abstract void hit(final Decks decks);

    public int calculateTotalScore() {
        return hand.sum();
    };

    public boolean isBust() {
        return hand.isOverBlackJack();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }
}

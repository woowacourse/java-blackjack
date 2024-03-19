package domain;

import java.util.List;

public abstract class Gamer {
    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getTotalScore() {
        return hand.getResultScore();
    }

    public int getHandCount() {
        return hand.getHandCount();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}

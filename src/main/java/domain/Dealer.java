package domain;

import domain.card.Card;

public class Dealer {
    private final Hand hand;

    public Dealer() {
        hand = new Hand();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return hand.getSum();
    }

    public Card getFirstCard() {
        return hand.getFirst();
    }
}

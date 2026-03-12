package domain;

import domain.card.Card;

public class Player {
    private final String name;
    private final Hand hand;

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return hand.getSum();
    }
}

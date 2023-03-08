package model.user;

import model.card.Card;

public class Inventory {

    private final Hand hand;
    private final int money;

    public Inventory(Hand hand, int money) {
        this.hand = hand;
        this.money = money;
    }

    public void addCard(final Card card) {
        this.hand.receiveCard(card);
    }

    public int getCardTotalValue() {
        return hand.getTotalValue();
    }

    public Hand getHand() {
        return hand;
    }
}

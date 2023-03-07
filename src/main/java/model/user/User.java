package model.user;

import model.card.Card;

public class User {

    private final String name;
    private final Hand hand;

    public User(final String name) {
        this.name = name;
        this.hand = Hand.create();
    }

    public void receiveCard(final Card card) {
        this.hand.receiveCard(card);
    }

    public int calculateTotalValue() {
        return hand.getTotalValue();
    }

    public Score judgeResult(int dealerTotalValue) {
        final int playerTotalValue = calculateTotalValue();
        return Score.judge(dealerTotalValue, playerTotalValue);
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getCardTotalValue() {
        return hand.getTotalValue();
    }
}

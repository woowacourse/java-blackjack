package model.user;

import model.card.Card;

public class User {

    private final String name;
    private final Inventory inventory;

    public User(final String name, final int money) {
        this.name = name;
        this.inventory = new Inventory(Hand.create(), money);
    }

    public void receiveCard(final Card card) {
        this.inventory.addCard(card);
    }

    public Score judgeResult(int dealerTotalValue) {
        final int playerTotalValue = getCardTotalValue();
        return Score.judge(dealerTotalValue, playerTotalValue);
    }

    public String getName() {
        return this.name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getCardTotalValue() {
        return inventory.getCardTotalValue();
    }
}

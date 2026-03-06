package domain;

import view.Message;

public class User {
    private String username;
    private Hand hand;

    public User(String name) {
        this.username = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getName() {
        return username;
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public String formatAskGetExtraCard() {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, username);
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }
}

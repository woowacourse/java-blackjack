package domain;

import view.Message;

public class User {
    private final String name;
    private final Hand hand;

    public User(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getName() {
        return name;
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public String getFormatedAskGetExtraCard() {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, name);
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }

    public String getUserFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public int getHand() {
        return hand.getTotalScore();
    }
}

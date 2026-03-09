package domain;

import view.Message;
import vo.Name;

public class User {
    private final Name name;
    private final Hand hand;

    public User(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getName() {
        return name.getName();
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public String getFormatedAskGetExtraCard() {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, name.getName());
    }

    public String getUserFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public int getHand() {
        return hand.calculateTotalScore();
    }
}

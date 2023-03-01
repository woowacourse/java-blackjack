package domain;

import java.util.List;

public class Participant {
    public Hand hand;

    public Participant(List<Card> cards) {
        this.hand = new Hand(cards);
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int getHandValue() {
        return hand.calculateValue();
    }

    public List<String> getCardNames() {
        return hand.getCards();
    }
}

package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final List<Card> hand = new ArrayList<>();

    public Card reveal() {
        return hand.getFirst();
    }

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }
}

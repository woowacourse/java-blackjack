package domain;

import java.util.List;

public class Gamer {
    private final Hand hand = new Hand();

    public List<Card> getCards() {
        return hand.getCards();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }
}

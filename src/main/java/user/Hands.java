package user;

import java.util.List;
import card.Card;
import card.Deck;

public class Hands {
    private List<Card> hands;

    public Hands(Deck deck) {
        this.hands = deck.initialDraw();
    }

    public int size() {
        return hands.size();
    }
}

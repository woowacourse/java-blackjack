package user;

import card.Card;
import card.Deck;

import java.util.List;

public class Hands {
    private List<Card> hands;

    public Hands(Deck deck) {
        this.hands = deck.initialDraw();
    }

    public int size() {
        return hands.size();
    }

    public void draw(Deck deck) {
        hands.add(deck.draw());
    }
}

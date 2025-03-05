package domain;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Denomination denomination : Denomination.values()) {
                Card card = new Card(denomination, suit);
                deck.add(card);
            }
        }
    }

    public int size() {
        return deck.size();
    }
}

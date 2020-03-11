package card;

import java.util.List;

public class Deck {
    public static final int FIRST_INDEX = 0;
    private List<Card> deck;

    public Deck() {
        this.deck = DeckFactory.create();
    }

    public Card draw() {
        return deck.remove(FIRST_INDEX);
    }

    public boolean isSize(int size) {
        return deck.size() == size;
    }
}

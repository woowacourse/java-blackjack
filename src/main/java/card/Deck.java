package card;

import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck() {
        this.deck = DeckFactory.create();
    }
}

package domain;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> deck;

    private CardDeck(List<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck of() {
        List<Card> allCards = Card.getAllCards();
        Collections.shuffle(allCards);

        return new CardDeck(allCards);
    }

    public Card draw() {
        return deck.remove(0);
    }
}

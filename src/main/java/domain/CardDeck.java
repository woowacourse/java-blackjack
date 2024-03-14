package domain;

import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> deck;

    private CardDeck() {
        deck = Card.getAllCards();
    }

    public static CardDeck createShuffledDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffle();
        return cardDeck;
    }

    public static CardDeck createNotShuffledDeck() {
        return new CardDeck();
    }

    private void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Card draw() {
        return deck.remove(0);
    }
}

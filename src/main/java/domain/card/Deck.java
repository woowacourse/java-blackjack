package domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Deck {

    private static final int DECK_SIZE = 52;

    private final Deque<Card> deck;

    public Deck() {
        deck = new ArrayDeque<>();

        int denominationLength = Denomination.values().length;
        for (int i = 0; i < DECK_SIZE; i++) {
            Suit suit = Suit.values()[i / denominationLength];
            Denomination denomination = Denomination.values()[i % denominationLength];
            createDeckCards(suit, denomination);
        }
    }

    public void shuffle() {
        ArrayList<Card> deckCards = new ArrayList<>(deck);
        Collections.shuffle(deckCards);

        deck.clear();
        deck.addAll(deckCards);
    }

    public Card draw() {
        return deck.pop();
    }

    private void createDeckCards(Suit suit, Denomination denomination) {
        deck.add(new Card(denomination, suit));
    }

    public Deque<Card> getDeck() {
        return deck;
    }
}

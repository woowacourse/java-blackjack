package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

    public static final int DECK_SIZE = 52;

    private final Stack<Card> deck;

    public Deck() {
        deck = new Stack<>();

        int denominationLength = Denomination.values().length;
        for (int i = 0; i < DECK_SIZE; i++) {
            Suit suit = Suit.values()[i / denominationLength];
            Denomination denomination = Denomination.values()[i % denominationLength];
            createDeckCards(suit, denomination);
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card draw() {
        return deck.pop();
    }

    private void createDeckCards(Suit suit, Denomination denomination) {
        if (denomination.equals(Denomination.ACE)) {
            deck.add(new Ace(suit));
            return;
        }
        deck.add(new Card(denomination, suit));
    }

    public List<Card> getDeck() {
        return Collections.unmodifiableList(deck);
    }
}

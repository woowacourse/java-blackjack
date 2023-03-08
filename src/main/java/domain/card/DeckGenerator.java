package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DeckGenerator {

    public Deck generate() {
        return new Deck(generateCards());
    }

    private Stack<Card> generateCards() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            addCardBySuit(cards, suit);
        }
        shuffle(cards);
        return cards;
    }

    private void addCardBySuit(List<Card> cards, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(suit, denomination));
        }
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
